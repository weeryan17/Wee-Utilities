package com.weeryan17.dgc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

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
		listGroup.add(instance.getKey());
		for(String group: groups){
			listGroup.add(group);
		}
		
		String[][] roleSync = {
			(String[]) listGroup.toArray(),
			{"REMOVED"} //removed from github. gotta love that security.
		};
		
		try {
			instance.getOutputStream().writeObject(roleSync);
		} catch (IOException e) {
			instance.getLogger().log(Level.SEVERE, "Error while writing to output stream", e);
			Bukkit.getPluginManager().disablePlugin(instance);
		}
		for(Player p: Bukkit.getOnlinePlayers()){
			String[] playerGroups = perm.getPlayerGroups(p);
		}
	}

	@Override
	public void run() {
		this.update();
	}
}
