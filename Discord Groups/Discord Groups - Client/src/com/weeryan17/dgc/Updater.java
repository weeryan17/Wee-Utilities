package com.weeryan17.dgc;

import java.io.IOException;
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
		Permission perm = instance.getPermissions();
		String[] groups = perm.getGroups();
		
		ArrayList<String> listGroup = new ArrayList<String>();
		for(String group: groups){
			listGroup.add(group);
		}
		
		String[][] roleSync = {
			{instance.getKey(), "REMOVED"}, //removed from github. gotta love that security.
			(String[]) listGroup.toArray()
		};
		
		try {
			instance.getOutputStream().writeObject(roleSync);
		} catch (IOException e) {
			instance.getLogger().log(Level.SEVERE, "Error while writing to output stream", e);
			Bukkit.getPluginManager().disablePlugin(instance);
		}
		ArrayList<String[]> rawUserSync = new ArrayList<String[]>();
		String[] process = {
				instance.getKey(),
				"Removed"
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
		String[][] userSync = null;
		userSync = rawUserSync.toArray(userSync);
		try {
			instance.getOutputStream().writeObject(userSync);
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
