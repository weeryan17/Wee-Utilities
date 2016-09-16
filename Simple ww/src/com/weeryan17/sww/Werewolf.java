package com.weeryan17.sww;

import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.weeryan17.sc.util.ChatChannel;
import com.weeryan17.utilities.api.ConfigApi;
import com.weeryan17.utilities.api.PluginMannager;

public class Werewolf extends JavaPlugin {
	int id;
	ConfigApi api;
	public void onEnable(){
		PluginMannager man = new PluginMannager();
		id = man.registerPlugin(this);
		api = new ConfigApi(id);
		ChatChannel channel = new ChatChannel();
		channel.createChannel("Werewolf Chat", "%RED%[%DARK_PURPLE&Werewolf%RED%]", "DARK_BLUE", "sc");
		
	}
	
	public FileConfiguration getPlayerDataConfig(UUID player){
		return api.config(player.toString(), "Data/Players");
	}
	
	
}
