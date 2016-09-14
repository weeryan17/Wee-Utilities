package com.weeryan17.elv;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.weeryan17.elv.util.Elevator;

public class Events implements Listener {
	Elevators instance;
	public Events(Elevators instance){
		this.instance = instance;
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		Action action = e.getAction();
		if(action.equals(Action.PHYSICAL)){
			if(this.isElevator(e.getClickedBlock().getLocation())){
				
			}
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e){
		ItemStack item = e.getItemInHand();
		if(instance.isElevatorPlate(item)){
			Location loc = e.getBlock().getLocation();
			Elevator elv = new Elevator(instance);
			elv.addPlate(loc);
		}
	}
	
	public void onBreak(BlockBreakEvent e){
		if(this.isElevator(e.getBlock().getLocation())){
			
		}
	}
	
	public boolean isElevator(Location loc){
		return false;
	}
}
