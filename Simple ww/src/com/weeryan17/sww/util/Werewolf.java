package com.weeryan17.sww.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Werewolf {
	Player p;
	public Werewolf(Player p){
		this.p = p;
	}
	
	public boolean wolfState;
	public void toggleWolfState(){
		if(wolfState){
			this.setWolfState(false);
		} else {
			this.setWolfState(true);
		}
	}
	
	public void setWolfState(boolean wolfState){
		this.wolfState = wolfState;
		Disguiser dis = new Disguiser();
		if(wolfState){
			dis.UnDisguise(p);
		} else {
			dis.DisgusisePlayerAsWolf(p);
		}
	}
	
	public boolean skyOpen(){
		boolean skyOpen = true;
		int x = p.getLocation().getBlockX();
		int z = p.getLocation().getBlockZ();
		for(int y = p.getLocation().getBlockY(); y <= p.getWorld().getMaxHeight(); y++){
			Location loc = new Location(p.getWorld(), x, y, z);
			if(loc.getBlock().getType() != Material.AIR){
				skyOpen = false;
			}
		}
		return skyOpen;
	}
}
