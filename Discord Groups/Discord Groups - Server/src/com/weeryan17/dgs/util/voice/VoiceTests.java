package com.weeryan17.dgs.util.voice;

import com.weeryan17.dgs.DiscordGroups;

import sx.blah.discord.handle.audio.IAudioManager;
import sx.blah.discord.handle.audio.IAudioReceiver;

public class VoiceTests {
	DiscordGroups instance;
	public VoiceTests(DiscordGroups instance){
		this.instance = instance;
	}
	
	IAudioManager man;
	public void test(){
		man = instance.getMainGuild().getAudioManager();
		man.subscribeReceiver(new DiscordGroupsReceiver());
	}
}
