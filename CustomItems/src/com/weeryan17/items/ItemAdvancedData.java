package com.weeryan17.items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import com.weeryan17.utilities.api.ItemUtils;

public class ItemAdvancedData {
	Items instance;
	FileConfiguration itemConfig;
	ItemUtils util = new ItemUtils();
	public ItemAdvancedData(Items instance, String item){
		this.instance = instance;
		itemConfig = instance.getItemConfigration(item);
	}
	public String getItemName(){
		return itemConfig.getString("Item.data.name");
	}
	@SuppressWarnings("unchecked")
	public ArrayList<String> getItemLore(){
		return (ArrayList<String>) itemConfig.get("Item.data.lore");
	}
	public int getItemAmout(){
		return itemConfig.getInt("Item.data.amount");
	}
	public Material getItemMaterial(){
		return Material.getMaterial(itemConfig.getString("Item.data.material"));
	}
	public int getItemData(){
		return itemConfig.getInt("Item.data.subId");
	}
	public ItemStack buildItem(){
		return util.createItem(this.getItemMaterial(), this.getItemData(), this.getItemAmout(), this.getItemName(), this.getItemLore());
	}
}
