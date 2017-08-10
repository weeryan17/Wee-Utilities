package com.weeryan17.reward.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RewardPlayer {
	UUID uuid;
	List<ItemStack> rewards;
	
	public RewardPlayer(Player p){
		uuid = p.getUniqueId();
		rewards = new ArrayList<>();
	}
	
	public void addItem(ItemStack... items){
		for(ItemStack item : items){
			rewards.add(item);
		}
	}
	
	public List<ItemStack> getRewards(){
		return rewards;
	}
	
	public Player getPlayer(){
		return Bukkit.getPlayer(uuid);
	}
}
