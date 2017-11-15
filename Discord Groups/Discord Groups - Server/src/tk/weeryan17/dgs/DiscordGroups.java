package tk.weeryan17.dgs;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Timer;
import java.util.logging.Level;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.arsenarsen.githubwebhooks4j.GithubWebhooks4J;
import com.arsenarsen.githubwebhooks4j.WebhooksBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.patreon.API;

import io.sentry.Sentry;
import io.sentry.SentryClient;
import io.sentry.SentryClientFactory;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.EmbedBuilder;
import tk.weeryan17.dgs.commands.CommandManager;
import tk.weeryan17.dgs.commands.CommandsCommand;
import tk.weeryan17.dgs.commands.DiscordGroupsCommand;
import tk.weeryan17.dgs.commands.admin.GenerateCommand;
//import tk.weeryan17.dgs.commands.admin.PermissionsCommand;
import tk.weeryan17.dgs.commands.developer.EvalCommand;
import tk.weeryan17.dgs.commands.developer.StopCommand;
import tk.weeryan17.dgs.commands.developer.UpdateCommand;
import tk.weeryan17.dgs.listeners.PushListener;
import tk.weeryan17.dgs.listeners.discord.ChatListener;
import tk.weeryan17.dgs.listeners.discord.RandomListener;
//import tk.weeryan17.dgs.socket.SocketTimer;
import tk.weeryan17.dgs.util.Logging;
import tk.weeryan17.dgs.util.MessageUtil;
import tk.weeryan17.dgs.util.WebUpdate;
import tk.weeryan17.dgs.util.storage.Storage;
import tk.weeryan17.dgs.util.twitter.TwitterUtil;
import tk.weeryan17.dgs.util.versoning.PluginVersion;
import tk.weeryan17.dgs.util.voice.VoiceTests;
import tk.weeryan17.dgs.util.web.WebReciver;

public class DiscordGroups {
	public IDiscordClient client;

	Logging logger;

	String jarFile = "";

	public static void main(String[] args) {
		DiscordGroups discord = new DiscordGroups();
		discord.init();
	}

	String token = "";
	public Long guildId = 280175962769850369L; // This is the id of the main
												// guild.
	int shards;

	Properties prop;
	Properties versions;

	String version;
	
	public static Gson gson;

	public boolean permsReady = false;

	static DiscordGroups instance;
	
	private static SentryClient sentry;

	public void init() {
		gson = new GsonBuilder().setPrettyPrinting().create();
		
		Sentry.init();
		
		sentry = SentryClientFactory.sentryClient();
		
		File file = null;
		try {
			file = new File(DiscordGroups.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
		} catch (URISyntaxException e) {
			e.printStackTrace();
			System.exit(0);
		}
		if(file.isDirectory()) {
			jarFile = file.getAbsolutePath();
		} else {
			jarFile = file.getParentFile().getAbsolutePath();
		}
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();

		prop = new Properties();
		try {
			InputStream propIn = classloader.getResourceAsStream("hidden/bot.properties");
			prop.load(propIn);
		} catch (IOException e) {
			System.out.println("Error enounter loading propeties file");
			System.out.println(e.getMessage());
			for (StackTraceElement element : e.getStackTrace()) {
				System.out.println(element.getClassName() + " class generated an error on line "
						+ element.getLineNumber() + " in the method " + element.getMethodName() + "().");
			}
			System.exit(1);
		}

		versions = new Properties();
		try {
			InputStream propIn = classloader.getResourceAsStream("versions.properties");
			versions.load(propIn);
		} catch (IOException e) {
			System.out.println("Error enounter loading propeties file");
			System.out.println(e.getMessage());
			for (StackTraceElement element : e.getStackTrace()) {
				System.out.println(element.getClassName() + " class generated an error on line "
						+ element.getLineNumber() + " in the method " + element.getMethodName() + "().");
			}
			System.exit(1);
		}

		logger = new Logging(this);
		logger.log("jar loc: " + jarFile, false);

		JSONObject jsonVersions = null;

		try {
			jsonVersions = readJsonFromUrl("https://discordgroups.weeryan17.tk/api/version.json");
		} catch (JSONException e1) {
			logger.log("Error loading versions from site", Level.SEVERE, e1, false);
			System.exit(1);
		} catch (IOException e1) {
			logger.log("Error loading versions from site", Level.SEVERE, e1, false);
			System.exit(1);
		}

		JSONObject pluginVersions = jsonVersions.getJSONObject("plugin versions");

		Iterator<?> pluginKeys = pluginVersions.keys();

		while (pluginKeys.hasNext()) {
			String pluginVersionString = (String) pluginKeys.next();
			if (pluginVersions.get(pluginVersionString) instanceof JSONObject) {
				JSONObject version = (JSONObject) pluginVersions.get(pluginVersionString);
				String title = version.getString("title");
				JSONArray jsonDesc = version.getJSONArray("description");
				ArrayList<String> arrayDesc = new ArrayList<String>();
				for (int i = 0; i < jsonDesc.length(); i++) {
					String descPart = jsonDesc.getString(i);
					arrayDesc.add(descPart);
				}
				String[] description = new String[jsonDesc.length()];
				description = arrayDesc.toArray(description);
				String download = version.getString("download");
				new PluginVersion(pluginVersionString, title, description, download);
			}
		}

		PluginVersion.setLatest(jsonVersions.getString("latest plugin stable version"));
		PluginVersion.setHighest(jsonVersions.getString("latest plugin version"));

		// JSONObject botVersions = jsonVersions.getJSONObject("bot versions");
		// version = botVersions.getString("latest bot version");

		version = jsonVersions.getString("latest bot version");

		instance = this;
		token = prop.getProperty("token");
		try {
			client = new ClientBuilder().withToken(token).withRecommendedShardCount().login();
		} catch (DiscordException e) {
			System.out.println("Error Logging in!");
			System.out.println(e.getMessage());
			for (StackTraceElement element : e.getStackTrace()) {
				System.out.println(element.getClassName() + " class generated an error on line "
						+ element.getLineNumber() + " in the method " + element.getMethodName() + "().");
			}
		}
		shards = client.getShardCount();
		client.getDispatcher().registerListener(new ChatListener(this));
		client.getDispatcher().registerListener(new RandomListener(this));
	}

	IGuild mainGuild;

	String secret = "";

	CommandManager cmdMannage;

	Storage storage;

	String inviteLink;

	ArrayList<Long> ids;

	int users;
	static int userCurrent = 0;

	int progress = 0;

	int perms = 0;

	int bot = 0;

	int largeGuilds = 0;

	public void readyInit() {
		client.changePlayingText("Creating perms");
		mainGuild = client.getGuildByID(guildId);
		ids = new ArrayList<Long>();
		ids.add(215644829969809421L);
		ids.add(207629082257653760L);
		storage = new Storage(this);
		secret = prop.getProperty("secret");
		int port = Integer.valueOf(prop.getProperty("webhookPort"));
		WebhooksBuilder web = new WebhooksBuilder().onPort(port).withSecret(secret)
				.forRequest(prop.getProperty("requestSite"));// Port removed
																// from github
																// again
																// security
		web = web.addListener(new PushListener(this));
		final IChannel logChannel = mainGuild.getChannelByID(280177651392708608L);
		EmbedBuilder ebProgress = new EmbedBuilder();
		ebProgress.appendField("Phase", "Initilizing", true);
		ebProgress.appendField("Bot progress", progressBar(0), false);
		getLogger().log("Bot initilizing", false);
		final IMessage progress = logChannel.sendMessage(ebProgress.build());
		new Timer().schedule(new WebUpdate(this), 1000L, 1000L);
		@SuppressWarnings("unused")
		GithubWebhooks4J github;
		try {
			github = web.build();
		} catch (IOException e) {
			logger.log("Chouldn't build webhook", Level.SEVERE, e, true);
			System.exit(1);
		}
		int users = 0;
		for (IGuild guild : instance.client.getGuilds()) {
			users = users + guild.getUsers().size();
		}
		this.users = users;
		getLogger().log("All users: " + users, false);
		ebProgress = new EmbedBuilder();
		ebProgress.appendField("Phase", "Started perms", true);
		ebProgress.appendField("Bot progress", progressBar(4), false);
		bot = 4;
		ebProgress.appendField("Perms progress", progressBar(perms), false);
		progress.edit(ebProgress.build());
		instance.getLogger().log("Creating all the perms!", false);
		/*
		new Thread() {
			@Override
			public void run() {
				for (IGuild guild : client.getGuilds()) {
					for (IUser user : guild.getUsers()) {
						int perms = updateProgress();
						if (instance.perms != perms) {
							instance.perms = perms;
							EmbedBuilder ebProgress = new EmbedBuilder();
							ebProgress.appendField("Phase", "Working on perms", true);
							ebProgress.appendField("Bot progress", progressBar(bot), false);
							ebProgress.appendField("Perms progress", progressBar(perms), false);
							progress.edit(ebProgress.build());
						}
						GuildUser guildUser = GuildUser.getGuildUser(user, guild);
						new DiscordGroupsPermissions(guildUser);
						try {
							DiscordGroupsPermissions.updatePerms(guildUser, instance);
						} catch (Exception e) {
							instance.getLogger().log("Error updating perms!", Level.WARNING, e, false);
						}
					}
				}
				instance.getLogger().log("Done creating perms", false);
				permsReady = true;
				client.changePlayingText("^commands");
			}
		}.start();
		*/
		WebReciver website = new WebReciver(this);
		website.initWeb();
		cmdMannage = new CommandManager();
		cmdMannage.registerCommand("dg", new DiscordGroupsCommand(this));
		cmdMannage.registerCommand("eval", new EvalCommand(this));
		cmdMannage.registerCommand("commands", new CommandsCommand(this));
		//cmdMannage.registerCommand("permissions", new PermissionsCommand(this));
		cmdMannage.registerCommand("generate", new GenerateCommand(this));
		cmdMannage.registerCommand("update", new UpdateCommand(this));
		cmdMannage.registerCommand("stop", new StopCommand(this));
		// this.initPatreon();
		ebProgress = new EmbedBuilder();
		ebProgress.appendField("Phase", "Init voice", true);
		ebProgress.appendField("Bot progress", progressBar(6), false);
		bot = 6;
		ebProgress.appendField("Perms progress", progressBar(perms), false);
		progress.edit(ebProgress.build());
		new VoiceTests(this).test();
		/*
		TwitterUtil twitter = new TwitterUtil(this);
		twitter.tweet("Bot is up and running!");
		*/
		ebProgress = new EmbedBuilder();
		ebProgress.appendField("Phase", "Bot done", true);
		ebProgress.appendField("Bot progress", progressBar(10), false);
		bot = 10;
		ebProgress.appendField("Perms progress", progressBar(perms), false);
		progress.edit(ebProgress.build());
		logger.log("Bot initlized", false);
		/*
		new Thread() {
			public void run() {
				new SocketTimer(instance, Integer.valueOf(prop.getProperty("socketPort"))).initSocket();
			}
		}.run();
		*/
	}

	public void initPatreon() {
		String accesTolken = getProperties().getProperty("parteonAccess");

		API api = new API(accesTolken);
		JSONObject user = api.fetchUser();
		JSONObject campain = api.fetchCampaignAndPatrons();

		getLogger().log("User: " + user.toString(), false);
		getLogger().log("campain: " + campain.toString(), false);
	}

	public void continuePatreon() {

	}

	public IGuild getMainGuild() {
		return mainGuild;
	}

	public Logging getLogger() {
		return logger;
	}

	public CommandManager getCommandMannager() {
		return cmdMannage;
	}

	public Properties getProperties() {
		return prop;
	}

	public Properties getVersions() {
		return versions;
	}

	public String getVersion() {
		return version;
	}

	public Storage getStorage() {
		return storage;
	}

	public String getJarLoc() {
		return jarFile;
	}

	public String getInviteLink() {
		return inviteLink;
	}

	public MessageUtil getMessageUtil() {
		return new MessageUtil(this);
	}

	public ArrayList<Long> getDevelopersIds() {
		return ids;
	}

	public int getShards() {
		return shards;
	}

	public static DiscordGroups getStaticInstance() {
		return instance;
	}

	public int updateProgress() {
		userCurrent++;
		getLogger().log("Current user: " + userCurrent, false);
		float rawPer = (float) userCurrent / (float) users;
		int per = (int) (rawPer * 10f);
		double percent = rawPer * 100f;
		getLogger().log("Current percent: " + percent, false);
		return per;
	}

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		URL versions = new URL(url);
		HttpURLConnection httpcon = (HttpURLConnection) versions.openConnection();
		httpcon.addRequestProperty("User-Agent", "Mozilla/4.0");
		InputStream is = httpcon.getInputStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	/**
	 * Creates a progress bar.
	 * 
	 * @param progress
	 *            the progress
	 * @return The bar
	 */
	public String progressBar(int progress) {
		int begin = progress;
		int end = 10 - progress;
		return "[" + StringUtils.repeat('\u25AC', begin) + "]( )" + StringUtils.repeat('\u25AC', end);
	}
}
