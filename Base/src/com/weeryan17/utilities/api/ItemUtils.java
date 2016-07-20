package com.weeryan17.utilities.api;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
/**
 * Class for doing cool things with items.
 * 
 * @author weeryan17
 *
 */
public class ItemUtils {
	/**
	 * Creates an item.
	 * 
	 * @param material The material of the item.
	 * @param data item data you want the item to be at.
	 * @param amount amount of the item.
	 * @param name item name you want null for no name.
	 * @param lore the lore you want null for no lore.
	 * @return the item.
	 */
    public ItemStack createItem(Material material, int data, int amount, String name, ArrayList<String> lore) {
        ItemStack item = data == 0 ? new ItemStack(material, amount) : new ItemStack(material, amount, ((byte)data));
        ItemMeta meta = item.getItemMeta();
        if (name != null) {
            meta.setDisplayName(name);
        }
        if (lore != null) {
            meta.setLore(lore);
        }
        item.setItemMeta(meta);
        return item;
    }
}
