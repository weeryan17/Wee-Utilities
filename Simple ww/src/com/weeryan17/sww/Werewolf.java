package com.weeryan17.sww;

import org.bukkit.plugin.java.JavaPlugin;

import com.weeryan17.sc.util.ChatChannel;

public class Werewolf extends JavaPlugin {
	public void onEnable(){
		ChatChannel channel = new ChatChannel();
		channel.createChannel("Werewolf Chat", "%RED%[%DARK_PURPLE&Werewolf%RED%]", "DARK_BLUE", "sc");
		
	}
}
