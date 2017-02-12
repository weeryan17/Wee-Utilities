package com.weeryan17.dgs;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;

public class RandomListener {
	
	DiscordGroups discord;
	public RandomListener(DiscordGroups discord){
		this.discord = discord;
	}
	
	@EventSubscriber
	public void onReady(ReadyEvent e){
		discord.readyInit();
	}
}
