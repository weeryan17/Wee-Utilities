package com.weeryan17.items;

import org.bukkit.plugin.java.JavaPlugin;

import com.weeryan17.utilities.PluginMannager;
import com.weeryan17.utilities.api.ConfigApi;

public class Items extends JavaPlugin {
	PluginMannager mannager = new PluginMannager();
	static int id;
	public void onEnable(){
		id = mannager.registerPlugin(this);
	}
	ConfigApi api = new ConfigApi(id);
	public void loadConfigs(){
		
	}
	
}
