package tk.weeryan17.sww.util.tasks;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import tk.weeryan17.sww.WerewolfPlugin;
import tk.weeryan17.sww.util.FullMoonEvent;
import tk.weeryan17.sww.util.Werewolf;

public class WorldChecker implements Runnable {
	WerewolfPlugin instance;
	public WorldChecker(WerewolfPlugin instance){
		this.instance = instance;
	}
	
	boolean isFullmoon;
	@Override
	public void run() {
		for(World world: Bukkit.getWorlds()){
			long time = world.getFullTime();
			long days = time/24000;
			int phase = (int) (days % 8);
			if(phase == 0 && world.getTime() >= 13000){
				if(instance.getWerewolfMannager().playersInWorld(world)){
					for(Werewolf wolf: instance.getWerewolfMannager().getWerewolvesInWorld(world)){
						if(wolf.skyOpen() && !wolf.wolfState){
							wolf.setWolfState(true);
						}
					}
					if(!isFullmoon){
						FullMoonEvent fullmoon = new FullMoonEvent(world);
						Bukkit.getPluginManager().callEvent(fullmoon);
					}
				}
				isFullmoon = true;
			} else {
				isFullmoon = false;
				if(instance.getWerewolfMannager().playersInWorld(world)){
					if(instance.getWerewolfMannager().playersInWorld(world)){
						for(Werewolf wolf: instance.getWerewolfMannager().getWerewolvesInWorld(world)){
							if(wolf.wolfState){
								wolf.setWolfState(false);
							}
						}
					}
				}
			}
			if(phase == 0 && world.getTime() <= 13000 && world.getTime() > 12990){
				for(Player p : world.getPlayers()){
					instance.sendActionbar(p, "The full moon emerges");
				}
			}
		}
	}

}
