package com.weeryan17.dgs.util.voice;

import com.weeryan17.dgs.DiscordGroups;

import sx.blah.discord.handle.audio.IAudioManager;

public class VoiceTests {
	DiscordGroups instance;

	public VoiceTests(DiscordGroups instance) {
		this.instance = instance;
	}

	IAudioManager man;

	public void test() {
		instance.getLogger().log("Audio initilizing", false);
		instance.getMainGuild().getVoiceChannelByID(282221746629771264L).join();
		man = instance.getMainGuild().getAudioManager();
		man.subscribeReceiver(new DiscordGroupsReceiver(instance));
		instance.getLogger().log("Audio initilized", false);
	}
}
