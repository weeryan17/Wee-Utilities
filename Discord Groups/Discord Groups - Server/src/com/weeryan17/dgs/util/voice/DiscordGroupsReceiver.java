package com.weeryan17.dgs.util.voice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
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
	SpeakingUser speaking;
	public DiscordGroupsReceiver(DiscordGroups instance){
		this.instance = instance;
		speaking = new SpeakingUser();
	}
	ArrayList<Byte> bytes;
	@Override
	public void receive(byte[] audio, IUser user) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){

			@Override
			public void run() {
				if(speaking.isSpeaking(user)){
					if(bytes == null){
						 bytes = new ArrayList<Byte>();
						 for(byte rawByte: audio){
							 bytes.add(rawByte);
						 }
					}
				} else {
					instance.getLogger().log("User " + user.getName() +" is done speaking", true);
					if(bytes != null){
						Byte[] arrayBytes = (Byte[]) bytes.toArray();
						speachToText(ArrayUtils.toPrimitive(arrayBytes), user);
						bytes = null;
					}
				}
			}
			
		}, 100L);
		
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
