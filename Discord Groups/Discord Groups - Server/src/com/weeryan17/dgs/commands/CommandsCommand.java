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
			channel.setTypingStatus(true);
			EmbedBuilder builder = instance.getMessageUtil().getBaseEmbed(sender, channel);
			builder.appendField("General",
					"`dg` - Gives you info on the bot." + '\n'
							+ "`dg stats` - Gives you infomation on the Bot's resources." + '\n'
							+ "`commands` - Gives you this message.",
					false);
			builder.appendField("Admin", "`generate key` - Generates an ID for your Guild. Do this in a private channel"
					+ '\n' + "`permissions` - The permissions menu.", false);
			channel.sendMessage(builder.build());
			channel.setTypingStatus(false);
		} else {
			channel.setTypingStatus(true);
			channel.sendMessage(sender.mention() + " This command has no args.");
			channel.setTypingStatus(false);
		}
	}

}
