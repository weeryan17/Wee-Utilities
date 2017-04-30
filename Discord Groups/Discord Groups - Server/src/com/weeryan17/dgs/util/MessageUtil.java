package com.weeryan17.dgs.util;

import java.util.ArrayList;

import com.weeryan17.dgs.DiscordGroups;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

public class MessageUtil {

	DiscordGroups instance;

	public MessageUtil(DiscordGroups instance) {
		this.instance = instance;
	}

	public EmbedBuilder getBaseEmbed(IUser sender, IChannel channel) {
		EmbedBuilder builder = new EmbedBuilder();
		builder.withAuthorName("@Discord Groups#2320");
		builder.withAuthorUrl("https://github.com/weeryan17/Wee-Utilities/tree/master/Discord%20Groups");
		builder.withThumbnail("https://www.dropbox.com/s/ly3s749g2om8o7x/discordgroupsicon.png?dl=1");
		builder.withFooterText("Command requested by @" + sender.getName() + "#" + sender.getDiscriminator());
		if (!channel.isPrivate()) {
			IRole role = null;
			int pos = 0;
			for (IRole rawRole : instance.client.getOurUser().getRolesForGuild(channel.getGuild())) {
				int rawPos = rawRole.getPosition();
				if (rawPos > pos) {
					role = rawRole;
					pos = rawPos;
				}
			}
			builder.withColor(role.getColor());
		}
		return builder;
	}

	public Object getUserFromString(String string, IChannel channel) {
		if (string.substring(0, 2).equals("<@!") && string.substring(string.length() - 1).equals(">")) {
			String stringId = string.substring(2, string.length() - 1);
			try {
				Long id = Long.parseLong(stringId);
				return channel.getGuild().getUserByID(id);
			} catch (Exception e) {
				return "invalid";
			}
		} else {
			try {
				Long id = Long.parseLong(string);
				return channel.getGuild().getUserByID(id);
			} catch (Exception e) {
				ArrayList<IUser> users = (ArrayList<IUser>) channel.getGuild().getUsersByName(string, true);
				if (users.size() >= 2) {
					return "multiple";
				} else {
					try {
						return users.get(0);
					} catch (Exception e1) {
						return "invalid";
					}
				}
			}
		}
	}

	public Object getRoleFromString(String string, IChannel channel) {
		if (string.substring(0, 2).equals("<@&") && string.substring(string.length() - 1).equals(">")) {
			String stringId = string.substring(2, string.length() - 1);
			try {
				Long id = Long.parseLong(stringId);
				return channel.getGuild().getRoleByID(id);
			} catch (Exception e) {
				return "invalid";
			}
		} else {
			try {
				Long id = Long.parseLong(string);
				return channel.getGuild().getRoleByID(id);
			} catch (Exception e) {
				ArrayList<IRole> users = (ArrayList<IRole>) channel.getGuild().getRolesByName(string);
				if (users.size() >= 2) {
					return "multiple";
				} else {
					try {
						return users.get(0);
					} catch (Exception e1) {
						return "invalid";
					}
				}
			}
		}
	}
}
