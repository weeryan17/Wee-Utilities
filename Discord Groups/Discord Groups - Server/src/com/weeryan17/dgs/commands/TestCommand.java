package com.weeryan17.dgs.commands;

import com.weeryan17.dgs.DiscordGroups;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;

public class TestCommand implements DiscordGroupsCommandBase {
	
	DiscordGroups instance;
	public TestCommand(DiscordGroups instance){
		this.instance = instance;
	}

	@Override
	public void onCommand(String[] args, IChannel channel, IUser sender) {
		String message = "<@" + sender.getID() + "> you tested a thing!";
		channel.sendMessage(message);
	}

}
