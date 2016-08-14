package com.weeryan17.sc.util;

import java.util.HashMap;
import java.util.logging.Level;

import com.weeryan17.sc.Chat;
import com.weeryan17.utilities.api.MinecraftColor;

public class PluginChannel {
	Chat instance;
	public PluginChannel(Chat instance){
		this.instance = instance;
	}
	
	private String name;
	private String prefix;
	private String color;
	private String abreviation;
	static HashMap<String, PluginChannel> map = new HashMap<String, PluginChannel>();
	public static PluginChannel getChannel(String name){
		return map.get(name);
	}
	public void createChannel(String name, String rawPrefix, String rawColor, String abreviation){
		this.name = name;
		this.abreviation = abreviation;
		String prefix = "";
		for(String split : rawPrefix.split("%")){
			if(MinecraftColor.isColor(split)){
				split = "" + MinecraftColor.valueOf(split);
			}
			prefix = prefix + split;
		}
		this.prefix = prefix;
		String color = "";
		if(MinecraftColor.isColor(rawColor)){
			color = "" + MinecraftColor.valueOf(rawColor);
		} else {
			instance.getLogger().log(Level.WARNING, "The color defined for the chat " + name + "is invalid");
		}
		this.color = color;
		map.put(name, this);
	}
	
	public String getName(){
		return name;
	}
	
	public String getPrefix(){
		return prefix;
	}
	
	public String getColor(){
		return this.color;
	}
	
	public String getAbreviation(){
		return abreviation;
	}
	
}
