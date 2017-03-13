package com.weeryan17.dgs.commands;

import com.weeryan17.dgs.DiscordGroups;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

public class CommandsCommand implements DiscordGroupsCommandBase {

	DiscordGroups instance;

	public CommandsCommand(DiscordGroups instance) {
		this.instance = instance;
	}

	@Override
	public void onCommand(String[] args, IChannel channel, IUser sender) {
		if (args.length == 0) {
			EmbedBuilder builder = instance.getMessageUtil().getBaseEmbed(sender, channel);
			builder.appendField("General",
					"dg - Gives you info on the bot." + '\n'
							+ "dg stats - gives you info on the bots cpu usage and such." + '\n'
							+ "commands - Gives you this message.",
					false);
			builder.appendField("Admin", "generate key - Generates an id for your guild. Do this in a private channel"
					+ '\n' + "permissions - the permissions menu.", false);
			channel.sendMessage(builder.build());
		} else {
			channel.sendMessage(sender.mention() + " this command has no args.");
		}
	}

}
