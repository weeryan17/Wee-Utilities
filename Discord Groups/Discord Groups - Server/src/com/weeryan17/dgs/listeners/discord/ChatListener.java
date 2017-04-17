package com.weeryan17.dgs.listeners.discord;

import java.util.Arrays;

import com.vdurmont.emoji.EmojiManager;
import com.weeryan17.dgs.DiscordGroups;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;

public class ChatListener {

	DiscordGroups instance;

	public ChatListener(DiscordGroups instance) {
		this.instance = instance;
	}

	String space = "                 ";

	char prefix = '^';

	@EventSubscriber
	public void onMessage(MessageReceivedEvent e) {
		IMessage message = e.getMessage();
		String text = message.getContent();
		if (text.length() >= 1) {
			if (prefix == text.charAt(0)) {
				text = text.substring(1);
				String[] command = text.split(" ");
				String name = command[0];
				String[] args = Arrays.copyOfRange(command, 1, command.length);
				if (instance.getCommandMannager().isCommand(name)) {
					if (!message.getChannel().isPrivate()) {
						message.delete();
					}
					instance.getCommandMannager().dispatchCommand(name, args, e.getChannel(), e.getAuthor());
					String compressedArgs = "";
					if (!name.equals("eval")) {
						for (String arg : args) {
							compressedArgs = compressedArgs + "\n" + space + " * " + arg;
						}
					} else {
						for (String arg : args) {
							compressedArgs = compressedArgs + arg;
						}
					}
					instance.getLogger()
							.log("The user " + e.getAuthor().getName() + " who's id is " + e.getAuthor().getLongID() + "\n"
									+ space + "executed the command: " + name + "\n" + space + "With the args:"
									+ compressedArgs, false);
				} else {
					message.addReaction(EmojiManager.getForAlias("question"));
				}
			} else if (text.split(" ")[0].equals("<@" + instance.client.getOurUser().getLongID() + ">")) {
				String[] command = text.split(" ");
				String name = command[1];
				String[] args = Arrays.copyOfRange(command, 2, command.length);
				if (instance.getCommandMannager().isCommand(name.toLowerCase())) {
					if (!message.getChannel().isPrivate()) {
						message.delete();
					}
					instance.getCommandMannager().dispatchCommand(name, args, e.getChannel(), e.getAuthor());
					String compressedArgs = "";
					if (!name.toLowerCase().equals("eval")) {
						for (String arg : args) {
							compressedArgs = compressedArgs + "\n" + space + " * " + arg;
						}
					} else {
						for (String arg : args) {
							compressedArgs = compressedArgs + arg;
						}
					}
					instance.getLogger()
							.log("The user " + e.getAuthor().getName() + " who's id is " + e.getAuthor().getLongID() + "\n"
									+ space + "executed the command: " + name + "\n" + space + "With the args:" + "\n"
									+ compressedArgs, false);
				} else {
					message.addReaction(EmojiManager.getForAlias("question"));
				}
			}
		}
	}
}
