package com.weeryan17.dgs.commands;

import com.weeryan17.dgs.DiscordGroups;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;

public class DiscordGroupsCommand implements DiscordGroupsCommandBase {
	
	DiscordGroups instance;
	public DiscordGroupsCommand(DiscordGroups instance){
		this.instance = instance;
	}
	
	@Override
	public void onCommand(String[] args, IChannel channel, IUser sender) {
		channel.sendMessage("You tested something!");
	}
	
}
