package com.weeryan17.dgc;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.weeryan17.utilities.api.ConfigApi;
import com.weeryan17.utilities.api.PluginMannager;

public class DiscordGroupsPlugin extends JavaPlugin {
	Socket serverSocket;
	ConfigApi confApi;
	
	InputStream socketInStream;
	ObjectOutputStream socketObjects;
	
	public void onEnable(){
		PluginMannager man = new PluginMannager();
		int id = man.registerPlugin(this);
		confApi = new ConfigApi(id);
		String address = "REMOVED"; //Removed from github for security reasons. Although you can probably figure this one out when this is done.
		int port = 0; //Removed from github for security reasons.
		try {
			serverSocket = new Socket(address, port);
		} catch (IOException e) {
			getLogger().log(Level.SEVERE, "Choudn't connect to remote bot server. It might be down.", e);
			Bukkit.getPluginManager().disablePlugin(this);
		}
		try {
			socketInStream = serverSocket.getInputStream();
		} catch (IOException e) {
			getLogger().log(Level.SEVERE, "Choudn't get socket input stream.", e);
			Bukkit.getPluginManager().disablePlugin(this);
		}
		try {
			socketObjects = new ObjectOutputStream(serverSocket.getOutputStream());
		} catch (IOException e) {
			getLogger().log(Level.SEVERE, "Choudn't get socket object output stream.", e);
			Bukkit.getPluginManager().disablePlugin(this);
		}
		
		
	}
	
}
