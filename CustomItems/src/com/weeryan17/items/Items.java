package com.weeryan17.items;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.weeryan17.utilities.PluginMannager;
import com.weeryan17.utilities.api.ConfigApi;

public class Items extends JavaPlugin {
	PluginMannager mannager = new PluginMannager();
	ConfigApi api;
	static int id;
	public void onEnable(){
		id = mannager.registerPlugin(this);
		this.loadConfigs();
		api = new ConfigApi(id);
	}
	public ArrayList<String> files = new ArrayList<String>();
	public void loadConfigs(){
		File file = new File(this.getDataFolder() + "/Items");
		for(File fileThing : file.listFiles()){
			if(fileThing.getName().endsWith(".yml")){
				files.add(fileThing.getName().substring(fileThing.getName().length() - 4));
				api.reloadConfig(fileThing.getName().substring(fileThing.getName().length() - 4));
			} else {
				getLogger().log(Level.WARNING, "Invalid file found in items folder! please remove " + fileThing.getName() + " at location " + fileThing.getAbsolutePath());
			}
		}
	}
	public FileConfiguration getItemConfigration(String name){
		return api.config(name, "Items");
	}
	
}
