package com.weeryan17.dgs.util;

import java.util.HashMap;

import sx.blah.discord.handle.obj.IUser;

public class SpeakingUser {
	private static HashMap<IUser, Boolean> users = new HashMap<IUser, Boolean>();
	public void setUserSpeaking(IUser user, boolean isSpeaking){
		users.put(user, isSpeaking);
	}
	
	public boolean isSpeaking(IUser user){
		return users.get(user);
	}
}
