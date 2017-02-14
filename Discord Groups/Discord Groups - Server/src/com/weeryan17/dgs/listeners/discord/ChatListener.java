package com.weeryan17.dgs.listeners.discord;

import com.weeryan17.dgs.DiscordGroups;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;

public class ChatListener {
	
	DiscordGroups instance;
	public ChatListener(DiscordGroups instance){
		this.instance = instance;
	}
	
	String prefix = "^";
	@EventSubscriber
	public void onMessage(MessageReceivedEvent e){
		IMessage message = e.getMessage();
		String text = message.getContent();
		if(prefix.equals(text.codePointAt(0))){
			
		}
	}
}
