package tk.weeryan17.gui;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Events implements Listener {
	GUI instance;
	public Events(GUI instance){
		this.instance = instance;
	}
	@EventHandler
	public void onJoin(final PlayerJoinEvent e){
		
	}
}
