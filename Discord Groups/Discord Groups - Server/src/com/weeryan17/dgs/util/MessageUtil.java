package com.weeryan17.dgs.util;

import com.weeryan17.dgs.DiscordGroups;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

public class MessageUtil {
	
	DiscordGroups instance;
	public MessageUtil(DiscordGroups instance){
		this.instance = instance;
	}
	
	public EmbedBuilder getBaseEmbed(IUser sender, IChannel channel){
		EmbedBuilder builder = new EmbedBuilder();
		builder.withAuthorName("@Discord Groups#2320");
		builder.withAuthorUrl("https://github.com/weeryan17/Wee-Utilities/tree/master/Discord%20Groups");
		builder.withThumbnail("https://www.dropbox.com/s/89k1iq87r59tfg5/discordgroups.png?dl=1");
		builder.withFooterText("Command requested by " + sender.getDisplayName(channel.getGuild()));
		if(!channel.isPrivate()){
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
		}
		return builder;
	}
}
