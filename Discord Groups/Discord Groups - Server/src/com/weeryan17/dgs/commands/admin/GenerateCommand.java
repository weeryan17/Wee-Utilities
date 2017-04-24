package com.weeryan17.dgs.commands.admin;

import java.util.ArrayList;

import com.weeryan17.dgs.DiscordGroups;
import com.weeryan17.dgs.commands.DiscordGroupsCommandBase;
import com.weeryan17.dgs.permissions.DiscordGroupsPermissions;
import com.weeryan17.dgs.util.GuildUser;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

public class GenerateCommand implements DiscordGroupsCommandBase {
	ArrayList<Long> ids;
	DiscordGroups instance;

	public GenerateCommand(DiscordGroups instance) {
		this.instance = instance;
		ids = instance.getDevelopersIds();
	}

	@Override
	public void onCommand(String[] args, IChannel channel, IUser sender) {
		channel.setTypingStatus(true);
		if (args.length == 0) {
			EmbedBuilder embed = instance.getMessageUtil().getBaseEmbed(sender, channel);
			embed.appendField("Usage", "```\n^generate <add/remove> key <remove:key>\nremove must have a key\n```",
					false);
			channel.sendMessage(embed.build());
			channel.setTypingStatus(false);
		} else if (args.length == 1) {
			EmbedBuilder embed = instance.getMessageUtil().getBaseEmbed(sender, channel);
			switch (args[0]) {
			case "add": {
				embed.appendField("Usage", "```\n^generate add key\n```", false);
			}
				break;
			case "remove": {
				embed.appendField("Usage", "```\n^generate remove key <key>\n```", false);
			}
				break;
			default: {
				embed.appendField("Usage", "```\n^generate <add/remove> key <remove:key>\nremove must have a key\n```",
						true);
			}
			}
			channel.sendMessage(embed.build());
			channel.setTypingStatus(false);
		} else if (args.length == 2) {
			switch (args[0]) {
			case "add": {
				if (args[1].equals("key")) {
					DiscordGroupsPermissions perms = DiscordGroupsPermissions
							.getUserPermissions(GuildUser.getGuildUser(sender, channel.getGuild()));
					if (perms.hasPerm("dg.server.generate")) {
						String key = instance.getStorage().generateRandomID(channel.getGuild().getLongID());
						channel.sendMessage(sender.mention() + " your new key is ```" + key + "```");
						channel.setTypingStatus(false);
					} else {
						channel.sendMessage(sender.mention() + " missing permission: ```dg.server.generate```");
						channel.setTypingStatus(false);
					}
				} else {
					channel.sendMessage(
							sender.mention() + " You can only generate keys at this point anything else is invalid");
					channel.setTypingStatus(false);
				}
			}
				break;
			case "remove": {
				if (args[1].equals("key")) {
					DiscordGroupsPermissions perms = DiscordGroupsPermissions
							.getUserPermissions(GuildUser.getGuildUser(sender, channel.getGuild()));
					if (perms.hasPerm("dg.server.generate")) {
						Long guildId = instance.getStorage().getGuildIdFromKey(args[2]);
						if (guildId == channel.getGuild().getLongID()) {
							instance.getStorage().removeKey(args[2]);
							channel.sendMessage(sender.mention() + " sucessfuly removed key");
							channel.setTypingStatus(false);
						} else {
							channel.sendMessage(sender.mention() + " That key doesn't exist for your guild");
							channel.setTypingStatus(false);
						}
					}
				} else {
					channel.sendMessage(
							sender.mention() + " You can only remove keys at this time anything else is invalid");
					channel.setTypingStatus(false);
				}
			}
				break;
			default: {
				EmbedBuilder embed = instance.getMessageUtil().getBaseEmbed(sender, channel);
				embed.appendField("Usage", "```\n^generate <add/remove> key <remove:key>\nremove must have a key\n```", false);
				channel.sendMessage(embed.build());
				channel.setTypingStatus(false);
			}
			}
		}
	}

}
