package com.weeryan17.dgs;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.json.JSONObject;

import com.arsenarsen.githubwebhooks4j.GithubWebhooks4J;
import com.arsenarsen.githubwebhooks4j.WebhooksBuilder;
import com.patreon.API;
import com.weeryan17.dgs.commands.CommandMannager;
import com.weeryan17.dgs.commands.CommandsCommand;
import com.weeryan17.dgs.commands.DiscordGroupsCommand;
import com.weeryan17.dgs.commands.admin.GenerateCommand;
import com.weeryan17.dgs.commands.admin.PermissionsCommand;
import com.weeryan17.dgs.commands.admin.PinCommand;
import com.weeryan17.dgs.commands.developer.EvalCommand;
import com.weeryan17.dgs.commands.developer.StopCommand;
import com.weeryan17.dgs.commands.developer.UpdateCommand;
import com.weeryan17.dgs.listeners.PushListener;
import com.weeryan17.dgs.listeners.discord.ChatListener;
import com.weeryan17.dgs.listeners.discord.RandomListener;
import com.weeryan17.dgs.socket.SocketTimer;
import com.weeryan17.dgs.util.Logging;
import com.weeryan17.dgs.util.MessageUtil;
import com.weeryan17.dgs.util.Storage;
import com.weeryan17.dgs.util.twitter.TwitterUtil;
import com.weeryan17.dgs.util.voice.VoiceTests;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.util.DiscordException;

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
	
	static DiscordGroups instance;

	public void init() {
		String file = DiscordGroups.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		jarFile = file.substring(0, file.length() - 18);
		jarFile = "C:/Users/developer/Desktop/test";
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
		logger = new Logging(this);
		logger.log("jar loc: " + jarFile, false);
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

		new SocketTimer(this, Integer.valueOf(prop.getProperty("socketPort"))).initSocket();
	}

	IGuild mainGuild;

	String secret = "";

	CommandMannager cmdMannage;

	Storage storage;

	String inviteLink;

	ArrayList<Long> ids;
	public void readyInit() {
		client.changePlayingText("^commands");
		mainGuild = client.getGuildByID(guildId);
		ids = new ArrayList<Long>();
		ids.add(215644829969809421L);
		ids.add(207629082257653760L);
		secret = prop.getProperty("secret");
		int port = Integer.valueOf(prop.getProperty("webhookPort"));
		WebhooksBuilder web = new WebhooksBuilder().onPort(port).withSecret(secret)
				.forRequest(prop.getProperty("requestSite"));// Port removed
																// from github
																// again
																// security
		web = web.addListener(new PushListener(this));
		getLogger().log("Bot initilizing", true);
		@SuppressWarnings("unused")
		GithubWebhooks4J github;
		try {
			github = web.build();
		} catch (IOException e) {
			logger.log("Chouldn't build webhook", Level.SEVERE, e, true);
			System.exit(1);
		}
		cmdMannage = new CommandMannager();
		cmdMannage.registerCommand("dg", new DiscordGroupsCommand(this));
		cmdMannage.registerCommand("eval", new EvalCommand(this));
		cmdMannage.registerCommand("commands", new CommandsCommand(this));
		cmdMannage.registerCommand("permissions", new PermissionsCommand(this));
		cmdMannage.registerCommand("generate", new GenerateCommand(this));
		cmdMannage.registerCommand("update", new UpdateCommand(this));
		cmdMannage.registerCommand("pin", new PinCommand());
		cmdMannage.registerCommand("stop", new StopCommand(this));
		storage = new Storage(this);
		this.initPatreon();
		new VoiceTests(this).test();
		TwitterUtil twitter = new TwitterUtil(this);
		twitter.tweet("Bot is up and running!");
		logger.log("Bot initlized", true);
	}
	
	public void initPatreon(){
		String accesTolken = getProperties().getProperty("parteonAccess");
		
		API api = new API(accesTolken);
		JSONObject user = api.fetchUser();
		JSONObject campain = api.fetchCampaignAndPatrons();
		
		getLogger().log("User: " + user.toString(), false);
		getLogger().log("campain: " + campain.toString(), false);
	}
	
	public void continuePatreon(){
		
	}

	public IGuild getMainGuild() {
		return mainGuild;
	}

	public Logging getLogger() {
		return logger;
	}

	public CommandMannager getCommandMannager() {
		return cmdMannage;
	}
	
	public Properties getProperties() {
		return prop;
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
	
	public int getShards(){
		return shards;
	}
	
	public static DiscordGroups getStaticInstance(){
		return instance;
	}
}
