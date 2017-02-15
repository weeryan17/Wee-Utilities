package com.weeryan17.dgs;

import java.io.IOException;
import java.io.ObjectInputStream;
//import java.net.ServerSocket;
import java.net.Socket;

import com.arsenarsen.githubwebhooks4j.GithubWebhooks4J;
import com.arsenarsen.githubwebhooks4j.WebhooksBuilder;
import com.weeryan17.dgs.commands.CommandMannager;
import com.weeryan17.dgs.commands.DiscordGroupsCommand;
import com.weeryan17.dgs.listeners.WebhooksListener;
import com.weeryan17.dgs.listeners.discord.ChatListener;
import com.weeryan17.dgs.listeners.discord.RandomListener;
import com.weeryan17.dgs.util.Logging;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.util.DiscordException;

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
	
	public void init(){
		try {
			client = new ClientBuilder().withToken(token).login();
		} catch (DiscordException e) {
			e.printStackTrace();
		}
		/*ServerSocket serverSocket;
		*try {
		*	serverSocket = new ServerSocket(port);
		*	socket = serverSocket.accept();
		*	objectIn = new ObjectInputStream(socket.getInputStream());
		*} catch (IOException e) {
		*	e.printStackTrace();
		*}
		**/
		client.getDispatcher().registerListener(new ChatListener(this));
		client.getDispatcher().registerListener(new RandomListener(this));
	}
	
	IGuild mainGuild;
	
	Logging logger;
	
	String secret = "REMOVED"; //Removed from github for security reasons.
	
	CommandMannager cmdMannage;
	
	public void readyInit(){
		mainGuild = client.getGuildByID(guildId);
		logger = new Logging(this);
		logger.log("Bot initlized", true);
		WebhooksBuilder web = new WebhooksBuilder().onPort(6000).withSecret(secret);
		web = web.addListener(new WebhooksListener(this));
		@SuppressWarnings("unused")
		GithubWebhooks4J github;
		try {
			github = web.build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cmdMannage = new CommandMannager();
		cmdMannage.registerCommand("test", new DiscordGroupsCommand(this));
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
	
}
