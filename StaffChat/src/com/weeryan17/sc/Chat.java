package com.weeryan17.sc;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.weeryan17.utilities.api.ConfigApi;
import com.weeryan17.utilities.api.PluginMannager;

public class Chat extends JavaPlugin {
	static int id;
	ConfigApi api;
	public void onEnable(){
		PluginMannager mannager = new PluginMannager();
		id = mannager.registerPlugin(this);
		api = new ConfigApi(id);
		if(!this.getChannelsConfig().contains("Chats.")){
			api.saveDefaultConfigs("Channels", "", true);
		}
		
	}
	public FileConfiguration getChannelsConfig(){
		return api.config("Channels", "");
	}
}
