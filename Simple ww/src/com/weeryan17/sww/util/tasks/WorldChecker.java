package com.weeryan17.sww.util.tasks;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class WorldChecker implements Runnable {

	@Override
	public void run() {
		for(World world: Bukkit.getWorlds()){
			long time = world.getFullTime();
			long days = time/24000;
			int phase = (int) (days % 8);
			if(phase == 0 && world.getTime() >= 13000){
				
			}
		}
	}

}
