package com.weeryan17.dgs;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import com.arsenarsen.githubwebhooks4j.GithubWebhooks4J;
import com.arsenarsen.githubwebhooks4j.WebhooksBuilder;
import com.weeryan17.dgs.commands.CommandMannager;
import com.weeryan17.dgs.commands.DiscordGroupsCommand;
import com.weeryan17.dgs.commands.TestCommand;
import com.weeryan17.dgs.commands.wee.EvalCommand;
import com.weeryan17.dgs.listeners.PushListener;
import com.weeryan17.dgs.listeners.discord.ChatListener;
import com.weeryan17.dgs.listeners.discord.RandomListener;
import com.weeryan17.dgs.socket.SocketTimer;
import com.weeryan17.dgs.util.Logging;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.Image;

public class DiscordGroups {
	public IDiscordClient client;
	public static void main(String[] args) {
		DiscordGroups discord = new DiscordGroups();
		discord.init();
	}
	
	String token = "REMOVED"; //Removed from github for security reasons.
	public String guildId = "280175962769850369"; //This is the id of the main guild.
	
	Socket socket;
	int port = 0; //Removed from github for security reasons.
	
	ObjectInputStream objectIn;
	
	SystemTray tray;
	public static boolean hasTray = false;
	TrayIcon icon;
	
	public void init(){
		try {
			client = new ClientBuilder().withToken(token).login();
		} catch (DiscordException e) {
			e.printStackTrace();
		}
		client.getDispatcher().registerListener(new ChatListener(this));
		client.getDispatcher().registerListener(new RandomListener(this));
		
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			socket = serverSocket.accept();
			objectIn = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			this.getLogger().log("Server socket geerated an io Exception", Level.SEVERE, e, false);
			System.exit(1);
		}
		Timer timer = new Timer();
		timer.schedule(new SocketTimer(this, objectIn), 0, 100);
		
	}
	
	IGuild mainGuild;
	
	Logging logger;
	
	String secret = "REMOVED"; //Removed from github for security reasons.
	
	CommandMannager cmdMannage;
	
	public void readyInit(){
		client.changeAvatar(Image.forUrl("png", "https://www.dropbox.com/s/89k1iq87r59tfg5/discordgroups.png?dl=1"));
		mainGuild = client.getGuildByID(guildId);
		logger = new Logging(this);
		if(SystemTray.isSupported()){
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
		WebhooksBuilder web = new WebhooksBuilder().onPort(7000).withSecret(secret);
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
	
}
