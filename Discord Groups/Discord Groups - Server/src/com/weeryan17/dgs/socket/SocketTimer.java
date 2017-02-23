package com.weeryan17.dgs.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;

import com.weeryan17.dgs.DiscordGroups;

public class SocketTimer {
	DiscordGroups instance;
	ServerSocket serverSocket;

	public SocketTimer(DiscordGroups instance, int port) {
		this.instance = instance;
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			instance.getLogger().log("Error screating server socket", Level.SEVERE, e, false);
			System.exit(1);
		}
	}
	
	public void initSocket() {
		while (true) {
			Socket socket = null;
			try {
				socket = serverSocket.accept();
			} catch (IOException e) {
				instance.getLogger().log("Server socket generated an io Exception", Level.SEVERE, e, false);
				System.exit(1);
			}
			
			try {
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
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
				socket.close();
			} catch (ClassNotFoundException e) {
				instance.getLogger().log("Recived a class not found exception on socket", Level.SEVERE, e, false);
			} catch (IOException e) {
				instance.getLogger().log("Recived a IO exception on socket", Level.SEVERE, e, false);
			}
			
		}
	}

}
