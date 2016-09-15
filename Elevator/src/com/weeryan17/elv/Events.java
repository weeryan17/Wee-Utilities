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
import com.weeryan17.elv.util.Movement;
import com.weeryan17.utilities.api.XZ;

public class Events implements Listener {
	Elevators instance;
	public Events(Elevators instance){
		this.instance = instance;
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		Action action = e.getAction();
		if(action.equals(Action.PHYSICAL)){
			Location loc = e.getClickedBlock().getLocation();
			if(this.isElevator(loc)){
				XZ xz = new XZ(loc.getWorld(), loc.getBlockX(), loc.getBlockZ());
				Elevator elv = Elevator.getElevatorByXZ(xz);
				if(elv.getSizePlates() == 2){
					int max = 0;
					int min = 1000;
					int other;
					int y = loc.getBlockY();
					for(int i : elv.getYValues()){
						if(i >= max){
							max = i;
						}
					}
					for(int i : elv.getYValues()){
						if(i <= min){
							min = i;
						}
					}
					other = max == y ? min : max;
					Movement.moveSmothly(loc.getBlockY(), other, e.getPlayer());
				}
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
		XZ xz = new XZ(loc.getWorld(), loc.getBlockX(), loc.getBlockZ());
		return (Elevator.getElevatorByXZ(xz) != null);
	}
}
