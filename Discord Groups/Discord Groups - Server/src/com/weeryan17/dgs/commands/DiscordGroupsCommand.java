package com.weeryan17.dgs.commands;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

import com.weeryan17.dgs.DiscordGroups;

import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.IChannel;
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
			builder.appendDesc("```Markdown\nA bot made by weeryan17 for linking groups to discord```");
			builder.appendField("\u200B", "\u200B", false);
			builder.appendField("What is this?",
					"```This is a bot made by weeryan17 for linking groups from a minecraft server. The original plugin got a lot of attention so he re-wrote it```",
					true);
			builder.appendField("Who made it?", "```Markdown\nIf you didn't read above weeryan17 made it```", true);
			builder.appendField("Where can I found out more?",
					"```In our offical suport discord guild```\n[link](https://discord.gg/GkxJhFq)", true);
			builder.appendField("How can I help?", "```If you're a developer you can help out by making the changes you think are need by forking my project on github.```\n[link](https://github.com/weeryan17/Wee-Utilities/tree/master/Discord%20Groups)\n```I plan on making an isolated repo later but for now you're going to get a lot of extra stuff.```", true);
			builder.appendField("What am I'm not a developer and I want to help?", "```You can suggest stuff in the offical guild, and later I plan on having donation links. I also need some artists```", true);
			EmbedObject embed = builder.build();
			channel.sendMessage(embed);
			channel.setTypingStatus(false);
		} else if(args.length == 1){
			switch(args[0]){
			case "stats" :{
				channel.setTypingStatus(true);
				long bytesFree = Runtime.getRuntime().freeMemory();
				long usedBytes = Runtime.getRuntime().totalMemory() - bytesFree;
				long mbFree = bytesFree / 1024 / 1024;
				long mbUsed = usedBytes / 1024 / 1024;
				OperatingSystemMXBean mx = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
				double cpu = mx.getSystemLoadAverage()/mx.getAvailableProcessors();
				EmbedBuilder builder = instance.getMessageUtil().getBaseEmbed(sender, channel);
				builder.withTitle("**Discord Groups Stats**");
				builder.appendField("Cpu usage", cpu + "%", true);
				builder.appendField("Version", "N/A", true);
				builder.appendField("Memory Free", mbFree + "MB", true);
				builder.appendField("Memory Used", mbUsed + "MB", true);
				EmbedObject embed = builder.build();
				channel.sendMessage(embed);
				channel.setTypingStatus(false);
			}
			break;
			default:{
				channel.sendMessage("<@" + sender.getID() + "> Invalid argument.");
			}
			}
		} else {
			channel.sendMessage("<@" + sender.getID() + "> I don't have that many args.");
		}
	}

}
