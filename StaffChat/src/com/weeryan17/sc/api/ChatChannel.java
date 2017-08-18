package com.weeryan17.sc.api;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.weeryan17.utilities.api.MinecraftColor;
/**
 * Used to create and manage chat channels.
 * 
 * @author weeryan17
 *
 */
public class ChatChannel {
	
	private String name;
	private String prefix;
	private String color;
	private String abreviation;
	static HashMap<String, ChatChannel> nameMap = new HashMap<String, ChatChannel>();
	static HashMap<String, ChatChannel> abreviationMap = new HashMap<String, ChatChannel>();
	/**
	 * get's a channel by name
	 * 
	 * @param name The name of the channel
	 * @return the ChatChannel
	 */
	public static ChatChannel getChannelByName(String name){
		return nameMap.get(name);
	}
	
	/**
	 * Get's channel by it's abbreviation
	 * 
	 * @param abreviation the abbreviation.
	 * @return
	 */
	public static ChatChannel getChannelByAbreviation(String abreviation){
		return abreviationMap.get(abreviation);
	}
	
	/**
	 * Creates a new chat channel.
	 * 
	 * @param name The name of the channel.
	 * @param rawPrefix the prefix color code
	 * @param rawColor the chat color code
	 * @param abreviation the abreviation
	 */
	public void createChannel(String name, String rawPrefix, String rawColor, String abreviation){
		this.name = name;
		this.abreviation = abreviation;
		String prefix = "";
		for(String split : rawPrefix.split("%")){
			if(MinecraftColor.isColor(split)){
				System.out.println("Staff chat found color " + split);
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
	
	/**
	 * Gets the name of the channel
	 * 
	 * @return the name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Get s the channel prefix
	 * 
	 * @return the prefix
	 */
	public String getPrefix(){
		return prefix;
	}
	
	/**
	 * Gets the chat color of the channel
	 * 
	 * @return The channel color
	 */
	public String getColor(){
		return color;
	}
	
	/**
	 * get's the abbreviation assigned to this channel.
	 * 
	 * @return the abbreviation
	 */
	public String getAbreviation(){
		return abreviation;
	}
	
	/**
	 * broadcast a message from a player in thus channel
	 * 
	 * @param p the player
	 * @param messageRaw the message
	 */
	public void broadcastPlayerMessage(Player p, String messageRaw){
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
