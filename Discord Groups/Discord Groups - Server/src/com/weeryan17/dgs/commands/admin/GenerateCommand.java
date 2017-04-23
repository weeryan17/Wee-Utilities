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
			embed.appendDesc("```\n^generate <add/remove> key <remove:key>\nremove must have a key\n```");
			channel.sendMessage(sender.mention() + " Improper usage: ", embed.build());
			channel.setTypingStatus(false);
		} else if (args.length == 1) {
			switch (args[0]) {
			case "add": {
				EmbedBuilder embed = instance.getMessageUtil().getBaseEmbed(sender, channel);
				embed.appendDesc("```\n^generate add key\n```");
				channel.sendMessage(sender.mention() + " correct usage:", embed.build());
				channel.setTypingStatus(false);
			}
				break;
			case "remove": {
				EmbedBuilder embed = instance.getMessageUtil().getBaseEmbed(sender, channel);
				embed.appendDesc("```\n^generate remove key <key>\n```");
				channel.sendMessage(sender.mention() + " correct usage:", embed.build());
				channel.setTypingStatus(false);
			}
			break;
			default :{
				EmbedBuilder embed = instance.getMessageUtil().getBaseEmbed(sender, channel);
				embed.appendDesc("```\n^generate <add/remove> key <remove:key>\nremove must have a key\n```");
				channel.sendMessage(sender.mention() + "Invalid ussage!" , embed.build());
				channel.setTypingStatus(false);
			}
			}
		} else if (args.length == 2) {
			switch (args[0]) {
			case "add": {
				if (args[1].equals("key")) {
					DiscordGroupsPermissions perms = DiscordGroupsPermissions
							.getUserPermissions(new GuildUser(sender, channel.getGuild()));
					instance.getLogger().log("Checking if sender has permission", false);
					if (perms.hasPerm("dg.server.generate")) {
						instance.getLogger().log("Sender had perm trying to get key", false);
						String key = instance.getStorage().generateRandomID(channel.getGuild().getLongID());
						instance.getLogger().log("Got key!", false);
						channel.sendMessage(sender.mention() + " your new key is ```" + key + "```");
						channel.setTypingStatus(false);
					} else {
						channel.sendMessage(sender.mention() + " you don't have permission to use this");
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
							.getUserPermissions(new GuildUser(sender, channel.getGuild()));
					if (perms.hasPerm("dg.server.generate")) {
						Long guildId = instance.getStorage().getGuildIdFromKey(args[2]);
						if (guildId == channel.getGuild().getLongID()) {
							instance.getStorage().removeKey(args[2]);
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
			default :{
				EmbedBuilder embed = instance.getMessageUtil().getBaseEmbed(sender, channel);
				embed.appendDesc("```\n^generate <add/remove> key <remove:key>\nremove must have a key\n```");
				channel.sendMessage(sender.mention() + "Invalid ussage!" + embed.build());
				channel.setTypingStatus(false);
			}
			}
		}
	}

}
