package com.weeryan17.dgs.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.TimerTask;

import com.weeryan17.dgs.DiscordGroups;

public class SocketTimer extends TimerTask {
	DiscordGroups instance;
	ObjectInputStream in;
	public SocketTimer(DiscordGroups instance, ObjectInputStream in){
		this.instance = instance;
		this.in = in;
	}

	@Override
	public void run() {
		try {
			Object ob = in.readObject();
			instance.getLogger().log("Socket object: " + ob.toString(), true);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
