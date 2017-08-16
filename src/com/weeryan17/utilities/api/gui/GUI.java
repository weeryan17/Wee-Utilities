package com.weeryan17.utilities.api.gui;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import com.weeryan17.utilities.GUIListener;
/**
 * Used to make custom GUIs easier.
 * 
 * @author weeryan17
 *
 */
public class GUI {
	
	private static HashMap<String, GUI> guis = new HashMap<String, GUI>();
	
	private String name;
	
	private Inventory inv;
	
	/**
	 * Creates a new custom GUI
	 * 
	 * @param type The type of GUI you want
	 * @param name The name of the GUI
	 */
	public GUI(GUIType type, String name){
		switch(type){
		case ONE_ROW:{
			this.ChestGUI(9, name);
		}
		break;
		case TWO_ROW:{
			this.ChestGUI(18, name);
		}
		break;
		case THREE_ROW:{
			this.ChestGUI(21, name);
		}
		break;
		case FOUR_ROW:{
			this.ChestGUI(36, name);
		}
		break;
		case FIVE_ROW:{
			this.ChestGUI(45, name);
		}
		break;
		case SIX_ROW:{
			this.ChestGUI(54, name);
		}
		break;
		case THREE_BY_THREE:{
			this.DispenserGUI(name);
		}
		}
	}
	
	/**
	 * Method that creates a chest GUI.
	 * Only used internally.
	 * 
	 * @param size The size of the chest Inventory.
	 * @param name The name of the Inventory.
	 */
	private void ChestGUI(int size, String name){
		inv = Bukkit.createInventory(null, size, name);
		guis.put(name, this);
		this.name = name;
	}
	
	/**
	 * Method that creates a dispenser GUI.
	 * Only used internally.
	 * 
	 * @param name The name of the Inventory.
	 */
	private void DispenserGUI(String name){
		inv = Bukkit.createInventory(null, InventoryType.DISPENSER, name);
		guis.put(name, this);
		this.name = name;
	}
	
	/**
	 * Gets the GUI name. Mostly used for my GUIListener.
	 * 
	 * @return The name of the GUI.
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Sets an item in the GUI.
	 * 
	 * @param item The item.
	 * @param slot The slot to put it in.
	 */
	public void setItem(GUIItem item, int slot){
		inv.setItem(slot, item.getItemStack());
	}
	
	/**
	 * Starts the Listener. Should only be called once.
	 * 
	 * @param instance Your plugins instance.
	 */
	public void startListener(Plugin instance){
		instance.getServer().getPluginManager().registerEvents(new GUIListener(instance), instance);
	}
	
	public static boolean isGUI(String invName){
		return guis.containsKey(invName);
	}
}
