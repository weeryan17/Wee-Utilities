package com.weeryan17.dgs.commands.developer;

import java.util.ArrayList;

import com.weeryan17.dgs.DiscordGroups;
import com.weeryan17.dgs.commands.DiscordGroupsCommandBase;
import com.weeryan17.dgs.util.Update;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;

public class UpdateCommand implements DiscordGroupsCommandBase {
	ArrayList<String> ids;
	DiscordGroups instance;

	public UpdateCommand(DiscordGroups instance) {
		ids = instance.getDevelopersIds();
	}

	@Override
	public void onCommand(String[] args, IChannel channel, IUser sender) {
		if (ids.contains(sender.getID())) {
			if (args.length >= 1) {
				channel.sendMessage(sender.mention() + " learn how your own command works");
			}
			new Update(instance).update();
		}
	}

}
