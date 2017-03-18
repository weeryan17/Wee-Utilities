package com.weeryan17.dgs.util.voice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;

import org.apache.commons.lang3.ArrayUtils;

import com.weeryan17.dgs.DiscordGroups;
import com.weeryan17.dgs.util.SpeakingUser;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import sx.blah.discord.handle.audio.IAudioReceiver;
import sx.blah.discord.handle.obj.IUser;

public class DiscordGroupsReceiver implements IAudioReceiver {
	DiscordGroups instance;
	SpeakingUser speakers;
	public DiscordGroupsReceiver(DiscordGroups instance){
		this.instance = instance;
		speakers = new SpeakingUser();
	}
	ArrayList<byte[]> bytes;
	boolean speaking = false;
	@Override
	public void receive(byte[] audio, IUser user) {
		handleRecive(audio, user);
	}
	
	public void handleRecive(byte[] audio, IUser user){
		if(!speaking){
			bytes = new ArrayList<byte[]>();
			bytes.add(audio);
			while(speakers.isSpeaking(user)){
				speaking = true;
			}
			if(speaking){
				byte[] stt = null;
				for(byte[] rawAudio: bytes){
					if(stt == null){
						stt = rawAudio;
					} else {
						stt = ArrayUtils.addAll(stt, rawAudio);
					}
				}
				speachToText(stt, user);
				speaking = false;
			}
		} else {
			bytes.add(audio);
		}
	}
	
	public void speachToText(byte[] audio, IUser user){
		instance.getLogger().log("Audio recived", true);
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
			return;
		}
		recon.startRecognition(input);
		SpeechResult result;
		while((result = recon.getResult()) != null) {
			instance.getLogger().log("Speach Result: " + result + "\nFrom user " + user.getName(), true);
		}
	}

}
