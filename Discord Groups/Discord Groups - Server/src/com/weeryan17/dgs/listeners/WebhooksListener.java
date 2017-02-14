package com.weeryan17.dgs.listeners;

import com.arsenarsen.githubwebhooks4j.events.EventListener;
import com.arsenarsen.githubwebhooks4j.events.GithubEvent;
import com.weeryan17.dgs.DiscordGroups;

@SuppressWarnings("rawtypes")
public class WebhooksListener implements EventListener {
	DiscordGroups instance;
	public WebhooksListener(DiscordGroups instance){
		this.instance = instance;
	}
	
	@Override
	public void handle(GithubEvent event) {
		
	}
	
}
