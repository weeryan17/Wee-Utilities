package com.weeryan17.dgs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class DiscordGroups {
	public IDiscordClient client;
	public static void main(String[] args) {
		DiscordGroups discord = new DiscordGroups();
		discord.init();
	}
	
	String token = "REMOVED"; //Removed from github for security reasons.
	public String guildId = "280175962769850369"; //This is the id of the main guild. Used for logging to the discord server it's self.
	public String channelId = "280177651392708608"; //This is the id of the log channel. Used for logging to the discord server it's self.
	
	Socket socket;
	int port = 0; //Removed from github for security reasons.
	
	ObjectInputStream objectIn;
	
	public void init(){
		try {
			client = new ClientBuilder().withToken(token).login();
		} catch (DiscordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			socket = serverSocket.accept();
			objectIn = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		client.getDispatcher().registerListener(new ChatListener());
		client.getDispatcher().registerListener(new RandomListener(this));
	}
	
	String hour;
	
	public void readyInit(){
		this.log("Bot initilized");
	}
	
	@SuppressWarnings("unused")
	public void log(String message){
		System.out.println(message);
		IGuild mainGuild = client.getGuildByID(guildId);
		IChannel logChannel = mainGuild.getChannelByID(channelId);
		try {
			logChannel.sendMessage(message);
		} catch (MissingPermissionsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RateLimitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DiscordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Date dateobj = new Date();
		
		DateFormat dayf = new SimpleDateFormat("dd/MM/yy");
		String dayDate = dayf.format(dateobj);
		
		DateFormat hourf = new SimpleDateFormat("HH");
		String hourDate = hourf.format(dateobj);
		
		PrintWriter writer = null;
		
		if(!hourDate.equals(hour)){
			try {
				PrintWriter newWriter = new PrintWriter("C:/Users/developer/Documents/Logs/" + dayDate + "/" + hourDate + ".txt", "UTF-8");
				if(writer != null){
					writer.close();
				}
				writer = newWriter;
				newWriter.close();
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		writer.write(message);
	}

}
