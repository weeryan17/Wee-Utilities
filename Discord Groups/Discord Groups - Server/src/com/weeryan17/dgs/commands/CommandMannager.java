package com.weeryan17.dgs.commands;

import java.util.HashMap;

import sx.blah.discord.handle.obj.IChannel;

public class CommandMannager {
	static HashMap<String, DiscordGroupsCommandBase> commands;
	
	public CommandMannager(){
		commands = new HashMap<String, DiscordGroupsCommandBase>();
	}
	
	public void registerCommand(String name, DiscordGroupsCommandBase command){
		commands.put(name, command);
	}
	
	public void unregisterCommand(String name){
		commands.remove(name);
	}
	
	public void dispatchCommand(String name, String[] args, IChannel channel){
		commands.get(name).onCommand(args, channel);
	}
}
