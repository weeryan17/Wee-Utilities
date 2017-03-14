package com.weeryan17.dgc.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.logging.Level;

import org.bukkit.Bukkit;

import com.weeryan17.dgc.DiscordGroupsPlugin;

public class SocketListener {
	DiscordGroupsPlugin instance;
	Socket socket;
	public SocketListener(DiscordGroupsPlugin instance){
		this.instance = instance;
		socket = instance.getSocket();
	}
	public void initSocket(){
		while(true){
			InputStream socketInStream = null;
			try {
				socketInStream = socket.getInputStream();
			} catch (IOException e) {
				instance.getLogger().log(Level.SEVERE, "Choudn't get socket input stream!", e);
				Bukkit.getPluginManager().disablePlugin(instance);
			}
			
			try {
				socketInStream.close();
			} catch (IOException e) {
				instance.getLogger().log(Level.SEVERE, "Error closing in stream!", e);
			}
		}
	}
}
