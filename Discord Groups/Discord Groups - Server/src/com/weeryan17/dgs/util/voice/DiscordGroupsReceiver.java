package com.weeryan17.dgs.util.voice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import com.weeryan17.dgs.DiscordGroups;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import sx.blah.discord.handle.audio.IAudioReceiver;
import sx.blah.discord.handle.obj.IUser;

public class DiscordGroupsReceiver implements IAudioReceiver {
	DiscordGroups instance;
	public DiscordGroupsReceiver(DiscordGroups instance){
		this.instance = instance;
	}

	@Override
	public void receive(byte[] audio, IUser user) {
		InputStream input = new ByteArrayInputStream(audio);
		Configuration config = new Configuration();
		config.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		config.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
		config.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
		StreamSpeechRecognizer recon = null;
		try {
			recon = new StreamSpeechRecognizer(config);
		} catch (IOException e) {
			instance.getLogger().log("Error creating StreamSpeechRecognizer", Level.WARNING, e, true);
		}
		recon.startRecognition(input);
		SpeechResult result;
		while((result = recon.getResult()) != null) {
			instance.getLogger().log("Speach Result: " + result + "\nFrom user " + user.getName(), true);
		}
	}

}
