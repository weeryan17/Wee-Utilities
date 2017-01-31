package com.weeryan17.dgc;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class DiscordGroupsPlugin extends JavaPlugin {
	Socket serverSocket;
	public void onEnable(){
		String address = "REMOVED"; //Removed from github for security reasons. Although you can probably figure this one out when this is done.
		int port = 0; //Removed from github for security reasons.
		try {
			serverSocket = new Socket(address, port);
		} catch (IOException e) {
			getLogger().log(Level.SEVERE, "Choudn't connect to remote bot server. It might be down.", e);
			Bukkit.getPluginManager().disablePlugin(this);
		}
		
	}
	
}
