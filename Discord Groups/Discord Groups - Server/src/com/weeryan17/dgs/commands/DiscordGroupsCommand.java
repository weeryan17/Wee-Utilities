package com.weeryan17.dgs.commands;

import com.weeryan17.dgs.DiscordGroups;

import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IRole;
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
			EmbedBuilder builder = new EmbedBuilder();
			builder.withAuthorName("@Discord Groups#2320");
			builder.withAuthorUrl("https://github.com/weeryan17/Wee-Utilities/tree/master/Discord%20Groups");
			builder.withThumbnail("https://www.dropbox.com/s/89k1iq87r59tfg5/discordgroups.png?dl=1");
			builder.withTitle("**Discord Groups**");
			builder.appendDesc("A bot made by weeryan17 for linking groups to discord");
			builder.appendField("\u200B", "\u200B", false);
			builder.appendField("What is this?",
					"This is a bot made by weeryan17 for linking groups from a minecraft server. The original plugin got a lot of attention so he re-wrote it",
					true);
			builder.appendField("Who made it?", "If you didn't read above weeryan17 made it", true);
			builder.appendField("Where can I found out more?",
					"In our offical suport [discord guild](https://discord.gg/GkxJhFq)", true);
			builder.appendField("How can I help?", "If you're a developer you can help out by making the changes you think are need by forking my project on [github](https://github.com/weeryan17/Wee-Utilities/tree/master/Discord%20Groups). I plan on making an isolated repo later but for now you're going to get a lot of extra stuff", true);
			builder.appendField("What am I'm not a developer and I want to help?", "You can suggest stuff in the offical guild, and later I plan on having donation links. I also need some artists", true);
			builder.withFooterText("Command requested by " + sender.getDisplayName(channel.getGuild()));
			IRole role = null;
			int pos = 0;
			for(IRole rawRole : instance.client.getOurUser().getRolesForGuild(channel.getGuild())){
				int rawPos = rawRole.getPosition();
				if(rawPos > pos){
					role = rawRole;
					pos = rawPos;
				}
			}
			builder.withColor(role.getColor());
			EmbedObject embed = builder.build();
			channel.sendMessage(embed);
		} else if(args.length == 1){
			switch(args[0]){
			case "stats" :{
				
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
