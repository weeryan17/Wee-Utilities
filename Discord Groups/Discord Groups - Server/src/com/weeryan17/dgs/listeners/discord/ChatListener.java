package com.weeryan17.dgs.listeners.discord;

import java.util.Arrays;

import com.weeryan17.dgs.DiscordGroups;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;

public class ChatListener {
	
	DiscordGroups instance;
	public ChatListener(DiscordGroups instance){
		this.instance = instance;
	}
	
	char prefix = '^';
	@EventSubscriber
	public void onMessage(MessageReceivedEvent e){
		IMessage message = e.getMessage();
		String text = message.getContent();
		if(prefix == text.charAt(0)){
			message.delete();
			text = text.substring(1);
			String[] command = text.split(" ");
			String name = command[0];
			String[] args = Arrays.copyOfRange(command, 1, command.length);
			instance.getCommandMannager().dispatchCommand(name, args, e.getChannel());
		}
	}
}
