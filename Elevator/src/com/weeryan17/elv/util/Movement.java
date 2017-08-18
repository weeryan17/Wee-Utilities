package com.weeryan17.elv.util;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

public class Movement implements Runnable {
	//up = 2 down = 1
	static HashMap<Player, Integer> upDown = new HashMap<Player, Integer>();
	static HashMap<Player, Integer> toWhat = new HashMap<Player, Integer>();
	public static void moveSmothly(int y1, int y2, Player p){
		int direction = y1 > y2 ? 1 : 2;
		int loc = y2;
		upDown.put(p, direction);
		toWhat.put(p, loc);
		p.setFlying(true);
		p.setFlySpeed(0);
		p.setInvulnerable(true);
	}

	@Override
	public void run() {
		for(Player p : upDown.keySet()){
			if(upDown.get(p) == 1){
				Location pLoc = p.getLocation();
				World world = pLoc.getWorld();
				int x = pLoc.getBlockX();
				double y = pLoc.getY();
				int z = pLoc.getBlockZ();
				y = y - .1;
				Location loc = new Location(world, x, y, z, p.getLocation().getYaw(), p.getLocation().getPitch());
				p.teleport(loc);
				if(y <= toWhat.get(p)){
					upDown.remove(p);
					toWhat.remove(p);
					p.setFlying(false);
					p.setFlySpeed(.1F);
					p.setInvulnerable(false);
				}
			} else if(upDown.get(p) == 2){
				Location pLoc = p.getLocation();
				World world = pLoc.getWorld();
				int x = pLoc.getBlockX();
				double y = pLoc.getY();
				int z = pLoc.getBlockZ();
				y = y + .1;
				Location loc = new Location(world, x, y, z, p.getLocation().getYaw(), p.getLocation().getPitch());
				p.teleport(loc);
				if(y >= toWhat.get(p)){
					upDown.remove(p);
					toWhat.remove(p);
					p.setFlying(false);
					p.setFlySpeed(.1F);
					p.setInvulnerable(false);
				}
			}
		}
	}
		
	@EventHandler
	public void onLogout(PlayerQuitEvent e){
		if(upDown.containsKey(e.getPlayer()) || toWhat.containsKey(e.getPlayer())){
			Player p = e.getPlayer();
			upDown.remove(p);
			toWhat.remove(p);
			p.setFlying(false);
			p.setFlySpeed(.1F);
			p.setInvulnerable(false);
		}
	}
}
