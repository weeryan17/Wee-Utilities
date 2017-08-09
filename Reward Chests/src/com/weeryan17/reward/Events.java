package com.weeryan17.reward;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class Events implements Listener {
	public void onInteract(PlayerInteractEvent e){
		e.getClickedBlock();
	}
}
