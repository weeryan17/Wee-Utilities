package com.weeryan17.utilities;

import com.weeryan17.utilities.BaseCommand;
import org.bukkit.plugin.java.JavaPlugin;
/**
 * The main class for this plugin
 * 
 * @author weeryan17
 *
 */
public class Base extends JavaPlugin {
    public void onEnable() {
        BaseCommand cmd = new BaseCommand();
        this.getCommand("weeutilities").setExecutor(cmd);
        this.getCommand("wu").setExecutor(cmd);
    }
}
