package com.weeryan17.toolbox;

import org.bukkit.plugin.java.JavaPlugin;

import com.weeryan17.toolbox.util.InventoryMannager;
import com.weeryan17.utilities.api.PluginMannager;

public class ToolBox extends JavaPlugin {
	InventoryMannager man;
	int id;
	public void onEnable(){
		id = new PluginMannager().registerPlugin(this);
		man = new InventoryMannager();
	}
	
	public InventoryMannager getInvMannager(){
		return man;
	}
}
