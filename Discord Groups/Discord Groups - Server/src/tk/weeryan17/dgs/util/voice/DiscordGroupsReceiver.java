package tk.weeryan17.dgs.util.voice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;

import org.apache.commons.lang3.ArrayUtils;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import sx.blah.discord.handle.audio.IAudioReceiver;
import sx.blah.discord.handle.obj.IUser;
import tk.weeryan17.dgs.DiscordGroups;

public class DiscordGroupsReceiver implements IAudioReceiver {
	DiscordGroups instance;

	// SpeakingUser speakers;
	public DiscordGroupsReceiver(DiscordGroups instance) {
		this.instance = instance;
		// speakers = new SpeakingUser();
	}

	ArrayList<byte[]> bytes;
	boolean speaking = false;
	boolean reciving = false;
	Timer timer = null;

	public void handleRecive(byte[] audio, IUser user) {
		instance.getLogger().log("Handling reciving", false);
		if (!speaking) {
			speaking = true;
			instance.getLogger().log("Creating new bytes list", false);
			bytes = new ArrayList<byte[]>();
			bytes.add(audio);
			while (reciving) {
				instance.getLogger().log("Still reciving", false);
			}
			instance.getLogger().log("Done reciving", false);
			if (speaking) {
				byte[] stt = null;
				for (byte[] rawAudio : bytes) {
					if (stt == null) {
						stt = rawAudio;
					} else {
						stt = ArrayUtils.addAll(stt, rawAudio);
					}
				}
				speachToText(stt, user);
				speaking = false;
			}
		} else {
			instance.getLogger().log("Adding bytes to list", false);
			bytes.add(audio);
		}
	}

	/**
	 * Sends the data to voice reconition
	 * 
	 * @param audio
	 *            The byte[] audio
	 * @param user
	 *            The user who spoke
	 */
	public void speachToText(byte[] audio, IUser user) {
		instance.getLogger().log("Audio recived", false);
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
		StringBuilder sb = new StringBuilder();
		while ((result = recon.getResult()) != null) {
			sb.append(result.getHypothesis());
		}
		instance.getLogger().log("Speach Result: " + sb.toString() + "\nFrom user " + user.getName(), true);
	}

	@Override
	public void receive(byte[] audio, IUser user, char sequence, int timestamp) {
		instance.getLogger().log("Reciving audio", false);
		reciving = true;
		StringBuilder sb = new StringBuilder();
		for (byte thing : audio) {
			sb.append(thing + " ");
		}
		if (timer == null) {
			timer = new Timer();
		} else {
			timer.cancel();
			timer = new Timer();
		}
		new Thread() {
			@Override
			public void run() {
				handleRecive(audio, user);
			}
		}.start();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				instance.getLogger().log("User done speaking", false);
				reciving = false;
			}

		}, 3000L);
	}

}
