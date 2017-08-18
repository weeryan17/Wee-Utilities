package com.weeryan17.elv.util;

import java.util.ArrayList;
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
	HashMap<Integer, Location> plates = new HashMap<Integer, Location>();
	
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
	
	public void openElevatorMenu(Player p){
		ItemStack item = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta meta = (BookMeta) item.getItemMeta();
		String book = "These number corispond to the y level of the plates./n";
		for(int i = loc.getWorld().getMaxHeight(); i >= 1; i--){
			if(plates.containsKey(i)){
				book = book + i + "./n";
			}
		}
		meta.setPage(0, book);
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
			XZ xz = new XZ(loc.getWorld(), x, z);
			Elevator elv = Elevator.getElevatorByXZ(xz);
			elv.plates.put(y, loc);
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
			plates.put(y, loc);
		}
		instance.saveElevatorsConfig();
	}
	
	public int getSizePlates(){
		return plates.size();
	}
	
	public ArrayList<Integer> getYValues(){
		ArrayList<Integer> yValues = new ArrayList<Integer>();
		for(Location loc : plates.values()){
			yValues.add(loc.getBlockY());
		}
		return yValues;
	}
}
