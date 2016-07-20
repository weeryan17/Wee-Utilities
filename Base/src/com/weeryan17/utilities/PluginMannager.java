package com.weeryan17.utilities;

import java.util.ArrayList;
import org.bukkit.plugin.java.JavaPlugin;
/**
 * Integrates your plugin with mine.
 * 
 * @author weeryan17
 *
 */
public class PluginMannager {
    public static ArrayList<JavaPlugin> plugins = new ArrayList<JavaPlugin>();
    /**
     * Registers your plugin with mine and returns the id assigned to the plugin.
     * 
     * @param plugin Your plugin.
     * @return the id assigned to your plugin.
     */
    public int registerPlugin(JavaPlugin plugin) {
        plugins.add(plugin);
        return plugins.size() - 1;
    }
}
