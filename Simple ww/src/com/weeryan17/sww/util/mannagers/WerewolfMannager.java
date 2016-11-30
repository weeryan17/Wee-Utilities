package com.weeryan17.sww.util.mannagers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.weeryan17.sww.WerewolfPlugin;
import com.weeryan17.sww.util.Werewolf;

public class WerewolfMannager {
	WerewolfPlugin instance;
	HashMap<Player, Werewolf> werewolves = new HashMap<Player, Werewolf>();
	ArrayList<UUID> uuidList = new ArrayList<UUID>();
	public WerewolfMannager(WerewolfPlugin instance){
		if(!instance.getWerewolfListConfig().contains("Werewolves")){
			@SuppressWarnings("unchecked")
			ArrayList<UUID> werewolves = (ArrayList<UUID>) instance.getWerewolfListConfig().get("Werewolves");
			for(UUID uuid : werewolves){
				Player p = Bukkit.getPlayer(uuid);
				Werewolf werewolf = new Werewolf(p);
				this.werewolves.put(p, werewolf);
				uuidList.add(uuid);
			}
		}
		this.instance = instance;
	}
	
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
		return werewolves.containsKey(p) ? true : false;
	}

	public void setWerewolf(Player p){
		if(!this.isWerewolf(p)){
			werewolves.put(p, new Werewolf(p));
		}
	}
	
	public void addWerewolf(Player p){
		UUID uuid = p.getUniqueId();
		uuidList.add(uuid);
		instance.getWerewolfListConfig().set("Werewolves", uuidList);
		instance.saveWerewolfListConfig();
	}
	
	public boolean isSilverWepon(ItemStack item){
		
	}
}
