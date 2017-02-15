package com.weeryan17.dgs.listeners.discord;

import com.weeryan17.dgs.DiscordGroups;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;

public class RandomListener {
	
	DiscordGroups discord;
	public RandomListener(DiscordGroups discord){
		this.discord = discord;
	}
	
	@EventSubscriber
	public void onReady(ReadyEvent e){
		System.out.println("Ready event ativated");
		discord.readyInit();
	}
}
