package com.weeryan17.utilities.api;

import com.weeryan17.utilities.PluginMannager;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.logging.Level;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
/**
 * The class for easy use of custom configs.
 * 
 * @author weeryan17
 *
 */
public class ConfigApi {
    JavaPlugin plugin;
    HashMap<String, FileConfiguration> datas = new HashMap<String, FileConfiguration>();
    private FileConfiguration data;
    /**
     * The class for easy use of custom configs.
     * 
     * @param plugin Your plugin.
     * @deprecated Use your plugin id instead. The plugin id is gotten from PluginMannager.
     */
    @Deprecated
    public ConfigApi(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public ConfigApi(int id) {
        this.plugin = PluginMannager.plugins.get(id);
    }

    public FileConfiguration config(String name, String subFolder) {
        File config = subFolder == "" ? new File(this.plugin.getDataFolder(), String.valueOf(name) + ".yml") : new File(this.plugin.getDataFolder() + "\\" + subFolder, String.valueOf(name) + ".yml");
        if (this.datas.get(name) == null) {
            this.data = YamlConfiguration.loadConfiguration((File)config);
            InputStream defConfigStream = this.plugin.getResource(String.valueOf(name) + ".yml");
            if (defConfigStream != null) {
                @SuppressWarnings("deprecation")
				YamlConfiguration defConfig = YamlConfiguration.loadConfiguration((InputStream)defConfigStream);
                this.data.setDefaults((Configuration)defConfig);
            }
            this.datas.put(name, this.data);
        }
        return this.datas.get(name);
    }

    public void saveConfigs(String name, String subFolder) {
        File config = subFolder == "" ? new File(this.plugin.getDataFolder(), String.valueOf(name) + ".yml") : new File(this.plugin.getDataFolder() + "/" + subFolder, String.valueOf(name) + ".yml");
        try {
            this.plugin.getConfig().options().copyDefaults(true);
            this.config(name, subFolder).save(config);
            this.config(name, subFolder);
        }
        catch (IOException ex) {
            this.plugin.getLogger().log(Level.WARNING, "Couldn''t save {0}.yml", name);
        }
    }

    public void saveDefaultConfigs(String name, String subFolder, boolean replace) {
        String resource = subFolder == "" ? String.valueOf(name) + ".yml" : String.valueOf(subFolder) + "/" + name + ".yml";
        this.plugin.saveResource(resource, replace);
    }

    public void reloadConfig(String name) {
        this.datas.remove(name);
    }
}
