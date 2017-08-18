package com.weeryan17.reward;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.weeryan17.reward.command.RcCommand;
import com.weeryan17.utilities.api.CommandApi;
import com.weeryan17.utilities.api.ConfigApi;
import com.weeryan17.utilities.api.PluginMannager;

public class RewardChest extends JavaPlugin {
	private static int id;
	private ConfigApi api;
	public void onEnable(){
		PluginMannager mannager = new PluginMannager();
		id = mannager.registerPlugin(this);
		api = new ConfigApi(id);
		CommandApi cmdApi = new CommandApi();
		cmdApi.registerCommand(new RcCommand(this));
	}
	
	public void onDisable(){
		
	}
	
	FileConfiguration chestConfig;
	public FileConfiguration getChestConfig(){
		if(chestConfig != null){
			return chestConfig;
		} else {
			chestConfig = api.config("chest", "");
			return chestConfig;
		}
	}
	
	FileConfiguration playerConfig;
	public FileConfiguration getPlayerConfig(){
		if(playerConfig != null){
			return playerConfig;
		} else {
			playerConfig = api.config("chest", "");
			return playerConfig;
		}
	}
	
}
