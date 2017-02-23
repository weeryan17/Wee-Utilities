package com.weeryan17.dgs.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;

import com.weeryan17.dgs.DiscordGroups;

public class SocketTimer {
	DiscordGroups instance;
	Socket s;

	public SocketTimer(DiscordGroups instance, Socket s) {
		this.instance = instance;
		this.s = s;
	}

	public void initSocket() {
		while (true) {
			try {
				ObjectInputStream in = new ObjectInputStream(s.getInputStream());
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
				instance.getLogger().log("Recived a class not found exception on socket", Level.SEVERE, e, false);
			} catch (IOException e) {
				instance.getLogger().log("Recived a IO exception on socket", Level.SEVERE, e, false);
			}

		}
	}

}
