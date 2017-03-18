package com.weeryan17.dgs.listeners.discord;

import com.weeryan17.dgs.DiscordGroups;
import com.weeryan17.dgs.util.SpeakingUser;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.guild.role.RoleCreateEvent;
import sx.blah.discord.handle.impl.events.guild.role.RoleDeleteEvent;
import sx.blah.discord.handle.impl.events.guild.voice.user.UserSpeakingEvent;
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
		// TODO Interact with file system for perms.
	}

	@EventSubscriber
	public void onRoleDelete(RoleDeleteEvent e) {
		@SuppressWarnings("unused")
		IRole role = e.getRole();
		// TODO Interact with files system for perms.
	}
	
	@EventSubscriber
	public void onSpeak(UserSpeakingEvent e){
		SpeakingUser user = new SpeakingUser();
		while(e.isSpeaking()){
			user.setUserSpeaking(e.getUser(), true);
		}
		if(user.isSpeaking(e.getUser())){
			user.setUserSpeaking(e.getUser(), false);
		}
	}
}
