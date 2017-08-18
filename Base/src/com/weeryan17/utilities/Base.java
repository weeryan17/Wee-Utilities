package com.weeryan17.utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weeryan17.utilities.BaseCommand;

import tk.weeryan17.com.ava.objects.Conditions;
import tk.weeryan17.com.ava.objects.conditions.BredAnimalsConditions;
import tk.weeryan17.com.ava.objects.enums.Triggers;

import java.util.HashMap;

import org.bukkit.plugin.java.JavaPlugin;
/**
 * The main class for this plugin
 * 
 * @author weeryan17
 *
 */
public class Base extends JavaPlugin {
	public static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public void onEnable() {
        BaseCommand cmd = new BaseCommand();
        this.getCommand("weeutilities").setExecutor(cmd);
        this.getCommand("wu").setExecutor(cmd);
    }
    
    public void constructConditions(){
    	HashMap<String, Conditions> conditions = Triggers.getConditionsMap();
    	conditions.put("minecraft:bred_animals", new BredAnimalsConditions());
    }
}
