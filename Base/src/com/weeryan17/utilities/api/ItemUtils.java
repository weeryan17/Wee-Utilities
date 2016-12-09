package com.weeryan17.utilities.api;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.server.v1_11_R1.NBTTagCompound;
import net.minecraft.server.v1_11_R1.NBTTagInt;
import net.minecraft.server.v1_11_R1.NBTTagList;
import net.minecraft.server.v1_11_R1.NBTTagString;
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
    /**
     * Adds lore to an item.
     * 
     * @param item The item to add lore to.
     * @param lore The lore you want to add.
     * 
     * @return The new item with lore.
     */
    public ItemStack addLore(ItemStack item, ArrayList<String> lore){
    	ItemMeta meta = item.getItemMeta();
    	meta.setLore(lore);
    	item.setItemMeta(meta);
    	return item;
    }
    
    public ItemStack addAttribute(ItemStack item, Attribute attributeType, int amount, int operation){
    	String attributeName = null;
    	switch(attributeType){
		case ATTACK_DAMAGE: {
			attributeName = "generic.attackDamage";
		}
		break;
		case KOCKBACK_RES: {
			attributeName = "generic.knockbackResistance";
		}
		break;
		case MAX_HEALTH: {
			attributeName = "generic.maxHealth";
		}
		break;
		case MOVEMENT_SPEED: {
			attributeName = "generic.movementSpeed";
		}
    	}
    	net.minecraft.server.v1_11_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
    	NBTTagCompound nbtTag = nmsItem.getTag();
    	if(nbtTag == null){
    		nbtTag = new NBTTagCompound();
    		nmsItem.setTag(nbtTag);
    		nbtTag = nmsItem.getTag();
    	}
    	NBTTagList attributes = (NBTTagList) nbtTag.get("AttributeModifiers");
    	if(attributes == null){
    		attributes = new NBTTagList();
    	}
    	NBTTagCompound attribute = new NBTTagCompound();
    	attribute.set("AttributeName", new NBTTagString(attributeName));
    	attribute.set("Name", new NBTTagString(attributeName));
    	attribute.set("Amount", new NBTTagInt(amount));
    	attribute.set("Operation", new NBTTagInt(operation));
    	attribute.set("UUIDLeast", new NBTTagInt(1000));
    	attribute.set("UUIDMost", new NBTTagInt(10000));
    	attributes.add(attribute);
    	nbtTag.set("AttributeModifiers", attributes);
    	nmsItem.setTag(nbtTag);
    	
    	return CraftItemStack.asBukkitCopy(nmsItem);
    }
}
