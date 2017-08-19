package tk.weeryan17.dgs.util;

import java.util.ArrayList;

import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;

public class GuildUser {
	private static ArrayList<GuildUser> users = new ArrayList<GuildUser>();
	private static ArrayList<IUser> iusers = new ArrayList<IUser>();
	private static ArrayList<IGuild> iguilds = new ArrayList<IGuild>();
	private IUser user;

	private IGuild guild;

	@Deprecated
	public GuildUser(IUser user, IGuild guild) {
		this.user = user;
		this.guild = guild;
		users.add(this);
		iusers.add(user);
		iguilds.add(guild);
	}

	public static GuildUser getGuildUser(IUser user, IGuild guild) {
		if (iusers.contains(user) && iguilds.contains(guild)) {
			for (GuildUser guildUser : users) {
				IUser gotUser = guildUser.getUser();
				IGuild gotGuild = guildUser.getGuild();
				if (gotUser.equals(user) && gotGuild.equals(guild)) {
					return guildUser;
				}
			}
			return new GuildUser(user, guild);
		} else {
			return new GuildUser(user, guild);
		}
	}

	public IUser getUser() {
		return user;
	}

	public IGuild getGuild() {
		return guild;
	}
}
