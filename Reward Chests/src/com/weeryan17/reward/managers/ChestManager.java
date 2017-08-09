package com.weeryan17.reward.managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Block;

/*
 * Used to manage chests where players can pick up awards
 */
public class ChestManager {
	List<Block> chests = new ArrayList<>();
	public void addChest(Block block){
		chests.add(block);
	}
	
	public boolean isChest(Block block){
		return chests.contains(block);
	}
}
