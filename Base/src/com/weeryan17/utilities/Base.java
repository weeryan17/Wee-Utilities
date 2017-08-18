package com.weeryan17.utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weeryan17.utilities.BaseCommand;

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
}
