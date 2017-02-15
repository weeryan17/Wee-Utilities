package com.weeryan17.dgs.commands;

import sx.blah.discord.handle.obj.IChannel;

public interface DiscordGroupsCommandBase {
	public void onCommand(String[] args, IChannel channel);
}
