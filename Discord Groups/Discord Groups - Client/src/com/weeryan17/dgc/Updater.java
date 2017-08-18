package com.weeryan17.dgc;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;

import org.apache.commons.lang3.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.milkbowl.vault.permission.Permission;

public class Updater implements Runnable{
	DiscordGroupsPlugin instance;
	public Updater(DiscordGroupsPlugin instance){
		this.instance = instance;
	}
	
	public void update(){
		instance.getLogger().info("Started role sync");
		Socket rolesocket = null;
		try {
			rolesocket = new Socket(instance.getAddress(), instance.getPort());
		} catch (IOException e) {
			instance.getLogger().log(Level.SEVERE, "Choudn't connect to remote bot server! It might be down.", e);
			Bukkit.getPluginManager().disablePlugin(instance);
		}
		ObjectOutputStream roleout = null;
		try {
			roleout = new ObjectOutputStream(rolesocket.getOutputStream());
		} catch (IOException e) {
			instance.getLogger().log(Level.SEVERE, "Error creating output stream. Server might of whent down.", e);
			Bukkit.getPluginManager().disablePlugin(instance);
		}
		
		Permission perm = instance.getPermissions();
		String[] groups = perm.getGroups();
		
		ArrayList<String> listGroup = new ArrayList<String>();
		for(String group: groups){
			listGroup.add(group);
		}
		
		String[][] roleSync = {
			{instance.getKey(), "sync roles"}, //removed from github. gotta love that security.
			listGroup.toArray(new String[listGroup.size()])
		};
		
		try {
			roleout.writeObject(roleSync);
			roleout.close();
			rolesocket.close();
		} catch (IOException e) {
			instance.getLogger().log(Level.SEVERE, "Error while writing to output stream", e);
			Bukkit.getPluginManager().disablePlugin(instance);
		}
		
		instance.getLogger().info("Moving to user sync");
		
		Socket usersocket = null;
		try {
			usersocket = new Socket(instance.getAddress(), instance.getPort());
		} catch (IOException e) {
			instance.getLogger().log(Level.SEVERE, "Choudn't connect to remote bot server! It might be down.", e);
			Bukkit.getPluginManager().disablePlugin(instance);
		}
		ObjectOutputStream userout = null;
		try {
			userout = new ObjectOutputStream(usersocket.getOutputStream());
		} catch (IOException e) {
			instance.getLogger().log(Level.SEVERE, "Error creating output stream. Server might of whent down.", e);
			Bukkit.getPluginManager().disablePlugin(instance);
		}
		
		ArrayList<String[]> rawUserSync = new ArrayList<String[]>();
		String[] process = {
				instance.getKey(),
				"sync user"
		};
		rawUserSync.add(process);
		for(Player p: Bukkit.getOnlinePlayers()){
			String[] playerGroups = perm.getPlayerGroups(p);
			String UUID = p.getUniqueId().toString();
			String[] UUIDArray = {
					UUID
			};
			rawUserSync.add(ArrayUtils.addAll(UUIDArray, playerGroups));
		}
		String[][] userSync = new String[rawUserSync.size()][rawUserSync.get(0).length];
		userSync = rawUserSync.toArray(userSync);
		try {
			userout.writeObject(userSync);
			userout.close();
			usersocket.close();
		} catch (IOException e) {
			instance.getLogger().log(Level.SEVERE, "Error while writing to output stream", e);
			Bukkit.getPluginManager().disablePlugin(instance);
		}
	}

	@Override
	public void run() {
		this.update();
	}
}
