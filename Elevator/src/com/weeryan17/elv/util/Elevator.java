package com.weeryan17.elv.util;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import com.weeryan17.elv.Elevators;
import com.weeryan17.utilities.api.XZ;

public class Elevator {
	Elevators instance;
	public Elevator(Elevators instance){
		this.instance = instance;
	}
	
	private static HashMap<Integer, Elevator> idMap = new HashMap<Integer, Elevator>(); 
	private static HashMap<XZ, Elevator> locMap = new HashMap<XZ, Elevator>();
	
	int id;
	Location loc;
	int x;
	int z;
	
	public static Elevator getElevatorById(int i){
		return idMap.get(i);
	}
	
	public static Elevator getElevatorByXZ(XZ loc){
		return locMap.get(loc);
	}
	
	public void openElevatorMenu(Player p, XZ loc){
		ItemStack item = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta meta = (BookMeta) item.getItemMeta();
		Elevator elevator = Elevator.getElevatorByXZ(loc);
		
	}
	
	public void addPlate(Location loc){
		int x = loc.getBlockX();
		int y = loc.getBlockY();
		int z = loc.getBlockZ();
		boolean exists = false;
		int id = 0;
		ConfigurationSection elevators = instance.getElevatorsConfig().getConfigurationSection("Elevators.");
		for(String key : elevators.getKeys(false)){
			int configx = instance.getElevatorsConfig().getInt("Elevators." + key + ".x");
			int configz = instance.getElevatorsConfig().getInt("Elevators." + key + ".z");
			if(x == configx && z == configz){
				exists = true;
				id = Integer.valueOf(key);
			}
		}
		if(exists){
			instance.getElevatorsConfig().set("Elevators." + id + ".Plates", y);
		} else {
			id = elevators.getKeys(false).size() + 1;
			instance.getElevatorsConfig().set("Elevators." + id + ".x", x);
			instance.getElevatorsConfig().set("Elevators." + id + ".z", z);
			instance.getElevatorsConfig().set("Elevators." + id + ".Plates", y);
			this.x = x;
			this.z = z;
			idMap.put(id, this);
			XZ xz = new XZ(loc.getWorld(), x, z);
			locMap.put(xz, this);
		}
		instance.saveElevatorsConfig();
	}
}
