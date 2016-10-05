package com.weeryan17.sww.util.mannagers;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.World;
import org.bukkit.entity.Player;

import com.weeryan17.sww.util.Werewolf;

public class WerewolfMannager {
	static HashMap<Player, Werewolf> werewolves = new HashMap<Player, Werewolf>();
	public Werewolf getWerewolfByPlayer(Player p){
		return werewolves.get(p);
	}
	
	public ArrayList<Werewolf> getWerewolvesInWorld(World world){
		ArrayList<Werewolf> werewolves = new ArrayList<Werewolf>();
		ArrayList<Player> players = (ArrayList<Player>) world.getPlayers();
		for(Player p: players){
			if(this.isWerewolf(p)){
				werewolves.add(this.getWerewolfByPlayer(p));
			}
		}
		return werewolves;
	}
	
	public boolean isWerewolf(Player p){
		if(werewolves.containsKey(p)){
			return true;
		} else {
			return false;
		}
	}
}
