package com.weeryan17.dgs;

import java.awt.TrayIcon;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import com.arsenarsen.githubwebhooks4j.GithubWebhooks4J;
import com.arsenarsen.githubwebhooks4j.WebhooksBuilder;

import com.weeryan17.dgs.commands.CommandMannager;
import com.weeryan17.dgs.commands.CommandsCommand;
import com.weeryan17.dgs.commands.DiscordGroupsCommand;
import com.weeryan17.dgs.commands.TestCommand;
import com.weeryan17.dgs.commands.admin.GenerateCommand;
import com.weeryan17.dgs.commands.admin.PermissionsCommand;
import com.weeryan17.dgs.commands.developer.EvalCommand;
import com.weeryan17.dgs.listeners.PushListener;
import com.weeryan17.dgs.listeners.discord.ChatListener;
import com.weeryan17.dgs.listeners.discord.RandomListener;
import com.weeryan17.dgs.socket.SocketTimer;
import com.weeryan17.dgs.util.Logging;
import com.weeryan17.dgs.util.MessageUtil;
import com.weeryan17.dgs.util.Storage;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.util.BotInviteBuilder;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.Image;

public class DiscordGroups {
	public IDiscordClient client;

	String jarFile = "";
	public static void main(String[] args) {
		DiscordGroups discord = new DiscordGroups();
		discord.init();
	}
	
	String token = "";
	public String guildId = "280175962769850369"; //This is the id of the main guild.
	
	Properties prop;
	
	public void init(){
		String file = DiscordGroups.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		jarFile = file.substring(0, file.length() - 18);
		prop = new Properties();
		try {
			InputStream propIn = new FileInputStream(jarFile + "/bot.properties");
			prop.load(propIn);
		} catch (IOException e) {
			System.out.println("Error enounter loading propeties file");
			System.out.println(e.getMessage());
			for(StackTraceElement element: e.getStackTrace()){
				System.out.println(element.getClassName() + " class generated an error on line " + element.getLineNumber() + " in the method " + element.getMethodName() + "().");
			}
			System.exit(1);
		}
		token = prop.getProperty("token");
		try {
			client = new ClientBuilder().withToken(token).login();
		} catch (DiscordException e) {
			System.out.println("Error Logging in!");
			System.out.println(e.getMessage());
			for(StackTraceElement element: e.getStackTrace()){
				System.out.println(element.getClassName() + " class generated an error on line " + element.getLineNumber() + " in the method " + element.getMethodName() + "().");
			}
		}
		client.getDispatcher().registerListener(new ChatListener(this));
		client.getDispatcher().registerListener(new RandomListener(this));

		new SocketTimer(this, Integer.valueOf(prop.getProperty("socketPort"))).initSocket();
	}
	
	IGuild mainGuild;
	
	Logging logger;
	
	String secret = "";
	
	CommandMannager cmdMannage;
	
	Storage storage;
	
	boolean hasTray = false;
	TrayIcon icon;
	
	String inviteLink;
	
	ArrayList<String> ids;
	
	public void readyInit(){
		client.changeAvatar(Image.forUrl("png", "https://www.dropbox.com/s/89k1iq87r59tfg5/discordgroups.png?dl=1"));
		client.changePlayingText("^commands");
		//SystemTrayUtil trayUtil = new SystemTrayUtil(this);
		//icon = trayUtil.getIcon();
		mainGuild = client.getGuildByID(guildId);
		ids = new ArrayList<String>();
		ids.add("215644829969809421");
		logger = new Logging(this);
		secret = prop.getProperty("secret");
		int port = Integer.valueOf(prop.getProperty("webhookPort"));
		WebhooksBuilder web = new WebhooksBuilder().onPort(port).withSecret(secret).forRequest(prop.getProperty("requestSite"));//Port removed from github again security
		web = web.addListener(new PushListener(this));
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
		cmdMannage.registerCommand("test", new TestCommand(this));
		cmdMannage.registerCommand("eval", new EvalCommand(this));
		cmdMannage.registerCommand("commands", new CommandsCommand(this));
		cmdMannage.registerCommand("permissions", new PermissionsCommand());
		cmdMannage.registerCommand("generate", new GenerateCommand(this));
		storage = new Storage(this);
		if(hasTray){
			//trayUtil.getIcon().displayMessage("Discord Groups", "Bot up and running", MessageType.INFO);
		}
		logger.log("Bot initlized", true);
		BotInviteBuilder invite = new BotInviteBuilder(client);
		invite.withPermissions(client.getGuildByID(guildId).getRoleByID("285593733221711872").getPermissions());
		inviteLink = invite.build();
		logger.log("Invite link " + invite, true);
	}
	
	public IGuild getMainGuild(){
		return mainGuild;
	}
	
	public Logging getLogger(){
		return logger;
	}
	
	public CommandMannager getCommandMannager(){
		return cmdMannage;
	}
	
	public TrayIcon getIcon(){
		return icon;
	}
	
	public Properties getProperties(){
		return prop;
	}
	
	public Storage getStorage(){
		return storage;
	}
	
	public String getJarLoc(){
		return jarFile;
	}
	
	public String getInviteLink(){
		return inviteLink;
	}
	
	public boolean hasTray(){
		return hasTray;
	}
	
	public MessageUtil getMessageUtil(){
		return new MessageUtil(this);
	}
	
	public ArrayList<String> getDevelopersIds(){
		return ids;
	}
}
