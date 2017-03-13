package com.weeryan17.dgs.util;

import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;

public class GuildUser {
	private IUser user;

	private IGuild guild;

	public GuildUser(IUser user, IGuild guild) {
		this.user = user;
		this.guild = guild;
	}

	public IUser getUser() {
		return user;
	}

	public IGuild getGuild() {
		return guild;
	}
}
