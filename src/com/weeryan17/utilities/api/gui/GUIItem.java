package com.weeryan17.utilities.api.gui;

import java.util.HashMap;

import org.bukkit.inventory.ItemStack;
/**
 * A Special type of item.
 * 
 * @author weeryan17
 *
 */
public class GUIItem {
	
	static HashMap<String, GUIItem> guiItems = new HashMap<String, GUIItem>();
	
	private ItemStack item;
	
	private boolean movable;
	
	/**
	 * Creates a new GUI item.
	 * 
	 * @param name The name of the item. NOT it's display name.
	 * @param item The item that you want to turn into a GUIItem. I suggest making the item with ItemUtils.
	 */
	public GUIItem(String name, ItemStack item){
		this.item = item;
	}
	
	/**
	 * Gets the ItemStack that belongs to this.
	 * 
	 * @return The ItemStack.
	 */
	public ItemStack getItemStack(){
		return item;
	}
	
	/**
	 * Gets if the item is movable.
	 * Mostly used internaly.
	 * 
	 * @return If the item is movable.
	 */
	public boolean isMoveAble(){
		return movable;
	}
	
	/**
	 * Sets if the item can be moved.
	 * This is automatically set to false if it has an action on the item
	 * 
	 * @param movable if it's movable.
	 */
	public void setMoveable(boolean movable){
		this.movable = movable;
	}
	
	
}
