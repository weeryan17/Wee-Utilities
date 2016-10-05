package com.weeryan17.sww;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.weeryan17.sc.api.ChatChannel;
import com.weeryan17.sww.util.mannagers.PlayerMannager;
import com.weeryan17.sww.util.mannagers.WerewolfMannager;
import com.weeryan17.sww.util.tasks.WorldChecker;
import com.weeryan17.utilities.api.ConfigApi;
import com.weeryan17.utilities.api.PluginMannager;

public class WerewolfPlugin extends JavaPlugin {
	int id;
	ConfigApi api;
	int worldChecker;
	WerewolfMannager werewolfMannager;
	PlayerMannager playerMannager;
	public void onEnable(){
		PluginMannager man = new PluginMannager();
		id = man.registerPlugin(this);
		api = new ConfigApi(id);
		ChatChannel channel = new ChatChannel();
		WorldChecker worldChecker = new WorldChecker();
		this.worldChecker = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, worldChecker, 0L, 10L);
		channel.createChannel("Werewolf Chat", "%RED%[%DARK_PURPLE&Werewolf%RED%]", "DARK_BLUE", "sc");
		werewolfMannager = new WerewolfMannager();
		playerMannager = new PlayerMannager();
	}
	
	public FileConfiguration getPlayerDataConfig(UUID player){
		return api.config(player.toString(), "Data/Players");
	}
	
	public void savePlayerDataConfig(UUID player){
		api.saveConfigs(player.toString(), "Data/Players");
	}
	
	public WerewolfMannager getWerewolfMannager(){
		return werewolfMannager;
	}
	
	public PlayerMannager getPlayerMannager(){
		return playerMannager;
	}
	
}
