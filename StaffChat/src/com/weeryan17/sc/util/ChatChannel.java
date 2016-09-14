package com.weeryan17.sc.util;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.weeryan17.utilities.api.MinecraftColor;

public class ChatChannel {
	
	private String name;
	private String prefix;
	private String color;
	private String abreviation;
	static HashMap<String, ChatChannel> nameMap = new HashMap<String, ChatChannel>();
	static HashMap<String, ChatChannel> abreviationMap = new HashMap<String, ChatChannel>();
	
	public static ChatChannel getChannelByName(String name){
		return nameMap.get(name);
	}
	
	public static ChatChannel getChannelByAbreviation(String abreviation){
		return abreviationMap.get(abreviation);
	}
	
	public void createChannel(String name, String rawPrefix, String rawColor, String abreviation){
		this.name = name;
		this.abreviation = abreviation;
		String prefix = "";
		for(String split : rawPrefix.split("%")){
			if(MinecraftColor.isColor(split)){
				split = "" + ChatColor.valueOf(split);
			}
			prefix = prefix + split;
		}
		this.prefix = prefix;
		String color = "";
		if(MinecraftColor.isColor(rawColor)){
			color = "" + ChatColor.valueOf(rawColor);
		} else {
			
		}
		this.color = color;
		nameMap.put(name, this);
		
		abreviationMap.put(abreviation, this);
	}
	
	public String getName(){
		return name;
	}
	
	public String getPrefix(){
		return prefix;
	}
	
	public String getColor(){
		return color;
	}
	
	public String getAbreviation(){
		return abreviation;
	}
	
	public void broadcastMessage(Player p, String messageRaw){
		String name = p .getName();
		String message = prefix + ChatColor.WHITE + name + color + ": " + messageRaw;
		String permision = "staffchat." + abreviation + ".listen";
		for(Player player : Bukkit.getOnlinePlayers()){
			if(player.hasPermission(permision)){
				player.sendMessage(message);
			}
		}
	}
	
}
