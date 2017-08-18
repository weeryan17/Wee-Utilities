package com.weeryan17.items;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class Events implements Listener {
	CustomItems instance;
	public Events(CustomItems instance){
		this.instance = instance;
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e){
		ItemStack item = e.getItemInHand();
		item.setAmount(1);
		if(item.equals(instance.coreItem)){
			instance.saveCoreBlock(e.getBlock());
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e){
		if(instance.isCoreBlock(e.getBlock())){
			e.setCancelled(true);
			instance.dropCoreBlock(e.getBlock());
		}
	}
}
