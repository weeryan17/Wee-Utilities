package com.weeryan17.sww.util;

import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public final class FullMoonEvent extends Event {
	
	private static final HandlerList handlers = new HandlerList();
    
	private World world;
	public FullMoonEvent(World world){
		this.world = world;
	}
	
	public World getWorld(){
		return world;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

}
