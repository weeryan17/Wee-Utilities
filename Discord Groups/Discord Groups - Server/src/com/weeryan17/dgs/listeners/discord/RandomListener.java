package com.weeryan17.dgs.listeners.discord;

import com.weeryan17.dgs.DiscordGroups;
import com.weeryan17.dgs.permissions.DiscordGroupsPermissions;
import com.weeryan17.dgs.util.GuildUser;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.guild.GuildCreateEvent;
import sx.blah.discord.handle.impl.events.guild.role.RoleCreateEvent;
import sx.blah.discord.handle.impl.events.guild.role.RoleDeleteEvent;
import sx.blah.discord.handle.impl.events.shard.ReconnectSuccessEvent;
import sx.blah.discord.handle.obj.IRole;
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
	public void onRoleCreate(RoleCreateEvent e) {
		@SuppressWarnings("unused")
		IRole role = e.getRole();
	}

	@EventSubscriber
	public void onRoleDelete(RoleDeleteEvent e) {
		@SuppressWarnings("unused")
		IRole role = e.getRole();
	}

	@EventSubscriber
	public void onReconect(ReconnectSuccessEvent e) {

	}
	
	@EventSubscriber
	public void onJoin(GuildCreateEvent e){
		for(IUser user: e.getGuild().getUsers()){
			DiscordGroupsPermissions.updatePerms(GuildUser.getGuildUser(user, e.getGuild()), discord);
		}
	}
}
