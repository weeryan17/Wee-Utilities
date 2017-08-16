package com.weeryan17.utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weeryan17.utilities.BaseCommand;
import com.weeryan17.utilities.api.advancement.objects.Conditions;
import com.weeryan17.utilities.api.advancement.objects.conditions.BredAnimalsConditions;
import com.weeryan17.utilities.api.advancement.objects.enums.Triggers;

import java.util.HashMap;

import org.bukkit.plugin.java.JavaPlugin;
/**
 * The main class for this plugin
 * 
 * @author weeryan17
 *
 */
public class Base extends JavaPlugin {
	static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
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
