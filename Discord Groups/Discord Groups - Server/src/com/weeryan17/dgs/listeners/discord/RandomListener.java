package com.weeryan17.dgs.listeners.discord;

import com.weeryan17.dgs.DiscordGroups;
import com.weeryan17.dgs.permissions.DiscordGroupsPermissions;
import com.weeryan17.dgs.util.GuildUser;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.guild.GuildCreateEvent;
import sx.blah.discord.handle.impl.events.guild.member.UserJoinEvent;
import sx.blah.discord.handle.impl.events.guild.member.UserLeaveEvent;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;

public class RandomListener {

	DiscordGroups discord;

	public RandomListener(DiscordGroups discord) {
		this.discord = discord;
	}

	@EventSubscriber
	public void onReady(ReadyEvent e) {
		discord.readyInit();
	}

	@EventSubscriber
	public void onJoin(GuildCreateEvent e) {
		for (IUser user : e.getGuild().getUsers()) {
			DiscordGroupsPermissions.updatePerms(GuildUser.getGuildUser(user, e.getGuild()), discord);
		}
	}

	@EventSubscriber
	public void onUserJoin(UserJoinEvent e) {
		IUser user = e.getUser();
		IGuild guild = e.getGuild();
		DiscordGroupsPermissions.updatePerms(GuildUser.getGuildUser(user, guild), discord);
	}

	@EventSubscriber
	public void onUserKick(UserLeaveEvent e) {
		IUser user = e.getUser();
		IGuild guild = e.getGuild();
		DiscordGroupsPermissions.updatePerms(GuildUser.getGuildUser(user, guild), discord);
	}
}
