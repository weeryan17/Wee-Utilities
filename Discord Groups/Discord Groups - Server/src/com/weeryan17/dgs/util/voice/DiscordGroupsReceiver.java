package com.weeryan17.dgs.util.voice;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import edu.cmu.sphinx.api.Configuration;
import sx.blah.discord.handle.audio.IAudioReceiver;
import sx.blah.discord.handle.obj.IUser;

public class DiscordGroupsReceiver implements IAudioReceiver {

	@Override
	public void receive(byte[] audio, IUser user) {
		InputStream input = new ByteArrayInputStream(audio);
		Configuration configuration = new Configuration();
	}

}
