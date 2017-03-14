package com.weeryan17.dgc;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.weeryan17.dgc.util.SocketListener;
import com.weeryan17.utilities.api.ConfigApi;
import com.weeryan17.utilities.api.PluginMannager;

import net.milkbowl.vault.permission.Permission;

public class DiscordGroupsPlugin extends JavaPlugin {
	Socket socket;
	ConfigApi confApi;
	
	InputStream socketInStream;
	ObjectOutputStream socketObjects;
	Permission perm;
	Updater update;
	
	public void onEnable(){
		//Defining everything!
		PluginMannager man = new PluginMannager();
		int id = man.registerPlugin(this);
		confApi = new ConfigApi(id);
		String address = "REMOVED"; //Removed from github for security reasons. Although you can probably figure this one out when this is done.
		int port = 0; //Removed from github for security reasons.
		try {
			socket = new Socket(address, port);
		} catch (IOException e) {
			getLogger().log(Level.SEVERE, "Choudn't connect to remote bot server! It might be down.", e);
			Bukkit.getPluginManager().disablePlugin(this);
		}
		
		try {
			socketObjects = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			getLogger().log(Level.SEVERE, "Choudn't get socket object output stream!", e);
			Bukkit.getPluginManager().disablePlugin(this);
		}
		
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
		perm = rsp.getProvider();
		
		//Acctuly stating stuff goes here
		update = new Updater(this);
		new SocketListener(this).initSocket();
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, update, 0, 1000L);
	}
	
	public void onDissable(){
		try {
			socket.close();
		} catch (IOException e) {
			getLogger().log(Level.SEVERE, "Error closing socket!", e);
		}
	}
	
	public Socket getSocket(){
		return socket;
	}
	
	public ObjectOutputStream getOutputStream(){
		return socketObjects;
	}
	
	public Updater getUpdater(){
		return update;
	}
}
