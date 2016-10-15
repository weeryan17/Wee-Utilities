package com.weeryan17.sww;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.weeryan17.sc.api.ChatChannel;
import com.weeryan17.sww.util.mannagers.WerewolfMannager;
import com.weeryan17.sww.util.tasks.WorldChecker;
import com.weeryan17.utilities.api.ConfigApi;
import com.weeryan17.utilities.api.PluginMannager;

import net.minecraft.server.v1_10_R1.IChatBaseComponent;
import net.minecraft.server.v1_10_R1.PacketPlayOutChat;

public class WerewolfPlugin extends JavaPlugin {
	int id;
	ConfigApi api;
	int worldChecker;
	WerewolfMannager werewolfMannager;
	public void onEnable(){
		PluginMannager man = new PluginMannager();
		id = man.registerPlugin(this);
		api = new ConfigApi(id);
		ChatChannel channel = new ChatChannel();
		WorldChecker worldChecker = new WorldChecker(this);
		this.worldChecker = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, worldChecker, 0L, 10L);
		channel.createChannel("Werewolf Chat", "%RED%[%DARK_PURPLE&Werewolf%RED%]", "DARK_BLUE", "sc");
		werewolfMannager = new WerewolfMannager(this);
	}
	
	public FileConfiguration getPlayerDataConfig(UUID player){
		return api.config(player.toString(), "Data/Players");
	}
	
	public void savePlayerDataConfig(UUID player){
		api.saveConfigs(player.toString(), "Data/Players");
	}
	
	public FileConfiguration getWerewolfListConfig(){
		return api.config("werewolves", "Data");
	}
	
	public void saveWerewolfListConfig(){
		api.saveConfigs("werewolves", "Data");
	}
	
	public WerewolfMannager getWerewolfMannager(){
		return werewolfMannager;
	}
	
	/*
	 * Thank you members on spigot for this code for action bars.
	 * This isn't my code.
	 */
	public void sendActionbar(Player p, String message) {
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', message) + "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte)2);
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(bar);
    }
	
}
