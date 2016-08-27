package com.weeryan17.sc;

import java.lang.reflect.Field;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.weeryan17.sc.util.ChatChannel;
import com.weeryan17.utilities.api.CommandApi;
import com.weeryan17.utilities.api.ConfigApi;
import com.weeryan17.utilities.api.PluginMannager;

public class Chat extends JavaPlugin {
	static int id;
	ConfigApi api;
	public void onEnable(){
		boolean checkconfig = true;
		PluginMannager mannager = new PluginMannager();
		id = mannager.registerPlugin(this);
		api = new ConfigApi(id);
		if(!this.getChannelsConfig().contains("Chats.")){
			api.saveDefaultConfigs("Channels", "Data", true);
			checkconfig = false;
			this.getLogger().log(Level.SEVERE, "config not found. you need to restart the server to properly enable staffchat");
		}
		CommandApi api = new CommandApi();
		if(checkconfig){
			ConfigurationSection section = this.getChannelsConfig().getConfigurationSection("Chats.");
			for(String key: section.getKeys(false)){
				String configkey = "." + key;
				String name = key;
				String prefix = this.getChannelsConfig().getString("Chats" + configkey + ".prefix");
				String color = this.getChannelsConfig().getString("Chats" + configkey + ".color");
				String abreviation = this.getChannelsConfig().getString("Chats" + configkey + ".abreviation");
				ChatChannel channel = new ChatChannel(this);
				channel.createChannel(name, prefix, color, abreviation);
				api.registerCommand(id, "/" + abreviation, "Toggles using the channel " + name, "staffchat." + abreviation + ".use");
				Field bukkitCommandMap;
				try {
					bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
					bukkitCommandMap.setAccessible(true);
					CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

					ChatCommand cmd = new ChatCommand(abreviation, this);
					commandMap.register(abreviation, cmd);
				} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		Events events = new Events(this);
		Bukkit.getServer().getPluginManager().registerEvents(events, this);
	}
	public FileConfiguration getChannelsConfig(){
		return api.config("Channels", "Data");
	}
	
	public FileConfiguration getPlayerDataConfig(){
		return api.config("Player", "Data");
	}
	
	public void savePlayerDataConfig(){
		api.saveConfigs("Player", "Data");
	}
}
