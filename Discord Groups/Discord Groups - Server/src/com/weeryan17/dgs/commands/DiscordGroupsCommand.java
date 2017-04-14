package com.weeryan17.dgs.commands;

import com.weeryan17.dgs.DiscordGroups;

import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IEmbed.IEmbedField;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

public class DiscordGroupsCommand implements DiscordGroupsCommandBase {

	DiscordGroups instance;

	public DiscordGroupsCommand(DiscordGroups instance) {
		this.instance = instance;
	}

	@Override
	public void onCommand(String[] args, IChannel channel, IUser sender) {
		if (args.length == 0) {
			channel.setTypingStatus(true);
			EmbedBuilder builder = instance.getMessageUtil().getBaseEmbed(sender, channel);
			builder.withTitle("**Discord Groups**");
			builder.appendDesc("```Markdown\nA bot made by weeryan17 for linking groups to Discord```");
			builder.appendField("\u200B", "\u200B", false);
			builder.appendField("Website", "[link](https://discordgroups.weeryan17.tk/)", true);
			builder.appendField("Dashboard (Part of site)", "[link](https://discordgroups.weeryan17.tk/dashboard/)", true);
			builder.appendField("Wiki", "[link](https://github.com/LeCodeCo/Discord-Groups-Wiki/wiki)", true);
			builder.appendField("Source code", "[link](https://github.com/weeryan17/Wee-Utilities/tree/discord-groups)",
					true);
			builder.appendField("Twitter", "[link](https://twitter.com/DiscordGroups)", true);
			builder.appendField("Support discord", "[link](https://discord.gg/GkxJhFq)", true);
			builder.appendField("\u200B", "\u200B", false);
			builder.appendField("QnA",
					"```> I want to help!\nSubmit feature requests in the Support Discord.```\n\n"
							+ "```> I need help setting this up!\nHelp can be recieved at the Support Discord.```\n\n"
							+ "```> Who is it by?\nThe main plugin and server work was by weeryan17 and the dashboard was by CodeCo.```",
					true);
			EmbedObject embed = builder.build();
			channel.sendMessage(embed);
			channel.setTypingStatus(false);
		} else if (args.length == 1) {
			switch (args[0]) {
			case "stats": {
				channel.setTypingStatus(true);
				long bytesFree = Runtime.getRuntime().freeMemory();
				long usedBytes = Runtime.getRuntime().totalMemory() - bytesFree;
				long bytesTotal = Runtime.getRuntime().totalMemory();
				long mbFree = bytesFree / 1024 / 1024;
				long mbUsed = usedBytes / 1024 / 1024;
				long mbTotal = bytesTotal / 1024 / 1024;
				// OperatingSystemMXBean mx =
				// ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
				// double cpu = mx.getSystemLoadAverage();
				EmbedBuilder builder = instance.getMessageUtil().getBaseEmbed(sender, channel);
				builder.withTitle("**Discord Groups Stats**");
				// builder.appendField("Cpu usage", cpu + "%", true);
				builder.appendField("Version", "N/A", true);
				builder.appendField("Memory Free", mbFree + "MB", true);
				builder.appendField("Memory Used", mbUsed + "MB", true);
				builder.appendField("Memory Total", mbTotal + "MB", true);
				builder.appendField("Shards", String.valueOf(instance.getShards()), true);
				builder.appendField("Guilds", String.valueOf(instance.client.getGuilds().size()), true);
				builder.appendField("Users", String.valueOf(instance.client.getUsers().size()), true);
				EmbedObject embed = builder.build();
				channel.sendMessage(embed);
				channel.setTypingStatus(false);
			}
				break;
			default: {
				channel.sendMessage("<@" + sender.getID() + "> Invalid argument.");
			}
			}
		} else {
			channel.sendMessage("<@" + sender.getID() + "> I don't have that many args.");
		}
	}

}
