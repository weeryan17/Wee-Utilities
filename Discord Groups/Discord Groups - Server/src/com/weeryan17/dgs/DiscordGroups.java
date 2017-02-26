package com.weeryan17.dgs;

//import java.awt.AWTException;
//import java.awt.MenuItem;
//import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
//import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;
//import java.util.logging.Level;

//import javax.imageio.ImageIO;

import com.arsenarsen.githubwebhooks4j.GithubWebhooks4J;
import com.arsenarsen.githubwebhooks4j.WebhooksBuilder;
import com.weeryan17.dgs.commands.CommandMannager;
import com.weeryan17.dgs.commands.DiscordGroupsCommand;
import com.weeryan17.dgs.commands.TestCommand;
import com.weeryan17.dgs.commands.developer.EvalCommand;
import com.weeryan17.dgs.listeners.PushListener;
import com.weeryan17.dgs.listeners.discord.ChatListener;
import com.weeryan17.dgs.listeners.discord.RandomListener;
import com.weeryan17.dgs.socket.SocketTimer;
import com.weeryan17.dgs.util.Logging;
import com.weeryan17.dgs.util.Storage;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IGuild;
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
	
	SystemTray tray;
	public static boolean hasTray = false;
	TrayIcon icon;
	
	Properties prop;
	
	public void init(){
		try {
			String file = DiscordGroups.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
			jarFile = file.substring(0, file.length() - 18);
		} catch (URISyntaxException e) {
			System.out.println("Getting jar location!");
			System.out.println(e.getMessage());
			for(StackTraceElement element: e.getStackTrace()){
				System.out.println(element.getClassName() + " class generated an error on line " + element.getLineNumber() + " in the method " + element.getMethodName() + "().");
			}
		}
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
	
	public void readyInit(){
		client.changeAvatar(Image.forUrl("png", "https://www.dropbox.com/s/89k1iq87r59tfg5/discordgroups.png?dl=1"));
		mainGuild = client.getGuildByID(guildId);
		logger = new Logging(this);
		/*if(SystemTray.isSupported()){
			getLogger().log("System tray is supported. Using!", true);
			PopupMenu popup = new PopupMenu();
			
			tray = SystemTray.getSystemTray();
			hasTray = true;
			
			java.awt.Image img = null;
			try {
				img = ImageIO.read(new File("C:/Users/developer/Dropbox/discordgroupstray.png"));
			} catch (IOException e) {
				getLogger().log("Choudln't read image", Level.SEVERE, e, false);
			}
			icon = new TrayIcon(img);
			
			MenuItem logItem = new MenuItem("Logs");
			
			popup.add(logItem);
			
			icon.setPopupMenu(popup);

			icon.displayMessage("Discord Groups", "Bot initilizing", MessageType.INFO);
			
			try {
				tray.add(icon);
			} catch (AWTException e) {
				getLogger().log("Error setting tray icon", Level.SEVERE, e, false);
			}
			
		} else {
			getLogger().log("System tray not supported. disableing system tray.", true);
		}
		*/
		secret = prop.getProperty("secret");
		int port = Integer.valueOf(prop.getProperty("webhookPort"));
		WebhooksBuilder web = new WebhooksBuilder().onPort(port).withSecret(secret);//Port removed from github again security
		web = web.addListener(new PushListener(this));
		@SuppressWarnings("unused")
		GithubWebhooks4J github;
		try {
			github = web.build();
		} catch (IOException e) {
			e.printStackTrace();
		}
		cmdMannage = new CommandMannager();
		cmdMannage.registerCommand("dg", new DiscordGroupsCommand(this));
		cmdMannage.registerCommand("test", new TestCommand(this));
		cmdMannage.registerCommand("eval", new EvalCommand(this));
		storage = new Storage(this);
		if(hasTray){
			icon.displayMessage("Discord Groups", "Bot up and running", MessageType.INFO);
		}
		logger.log("Bot initlized", true);
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
	
}
