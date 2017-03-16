package com.weeryan17.dgs.commands;

import java.util.HashMap;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;

public class CommandMannager {

	static HashMap<String, DiscordGroupsCommandBase> commands;

	public CommandMannager() {
		commands = new HashMap<String, DiscordGroupsCommandBase>();
	}

	public void registerCommand(String name, DiscordGroupsCommandBase command) {
		commands.put(name, command);
	}

	public void unregisterCommand(String name) {
		commands.remove(name);
	}

	public boolean isCommand(String name) {
		return commands.containsKey(name.toLowerCase());
	}

	public void dispatchCommand(String name, String[] args, IChannel channel, IUser user) {
		commands.get(name).onCommand(args, channel, user);
	}
}
