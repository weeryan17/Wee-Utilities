package com.weeryan17.dgs.listeners.discord;

import com.weeryan17.dgs.DiscordGroups;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.guild.role.RoleCreateEvent;
import sx.blah.discord.handle.impl.events.guild.role.RoleDeleteEvent;
import sx.blah.discord.handle.impl.events.shard.ReconnectSuccessEvent;
import sx.blah.discord.handle.obj.IRole;

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
	public void onReconect(ReconnectSuccessEvent e){
		
	}
}
