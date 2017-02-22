package com.weeryan17.dgs.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.TimerTask;
import java.util.logging.Level;

import com.weeryan17.dgs.DiscordGroups;

public class SocketTimer extends TimerTask {
	DiscordGroups instance;
	ObjectInputStream in;

	public SocketTimer(DiscordGroups instance, ObjectInputStream in) {
		this.instance = instance;
		this.in = in;
	}

	@Override
	public void run() {
		try {

			Object ob = in.readObject();
			if (ob instanceof String[]) {
				String[] stuff = (String[]) ob;
				for (String thing : stuff) {
					instance.getLogger().log("Socket object: " + thing, true);
				}
				in.read();
			} else {
				instance.getLogger().log("Object recived that wasn't a string", Level.WARNING, true);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
