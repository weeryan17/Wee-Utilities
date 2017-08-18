package com.weeryan17.dgc.util;

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
    HashMap<String, File> customFiles = new HashMap<String, File>();
    
    private FileConfiguration data;
    
    /**
     * The class for easy use of custom configs.
     * 
     * @param plugin Your plugin.
     */
    public ConfigApi(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    
    /**
     * Creates a new custom config.
     * 
     * @param name The name of the config.
     * @param subFolder The folder within your data folder that the config should go into.
     * @return The configuration file.
     */
    public FileConfiguration config(String name, String subFolder) {
        File config = subFolder.equals("") ? new File(this.plugin.getDataFolder(), String.valueOf(name) + ".yml") : new File(this.plugin.getDataFolder() + "/" + subFolder, String.valueOf(name) + ".yml");
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
    
    /**
     * Saves your custom config.
     * 
     * @param name The name of the config.
     * @param subFolder The folder within your data folder that the config should go into.
     */
    public void saveConfigs(String name, String subFolder) {
        File config = subFolder.equals("") ? new File(this.plugin.getDataFolder(), String.valueOf(name) + ".yml") : new File(this.plugin.getDataFolder() + "/" + subFolder, String.valueOf(name) + ".yml");
        try {
            this.plugin.getConfig().options().copyDefaults(true);
            this.config(name, subFolder).save(config);
            this.config(name, subFolder);
        }
        catch (IOException ex) {
            this.plugin.getLogger().log(Level.WARNING, "Couldn''t save {0}.yml", name);
        }
    }
    
    /**
     * Saves a config resource from your plugin into your data folder.
     * 
     * @param name The name of the resource.
     * @param subFolder The folder that the resource is in plus where it will be saved.
     * @param replace
     */
    public void saveDefaultConfigs(String name, String subFolder, boolean replace) {
        String resource = subFolder.equals("") ? String.valueOf(name) + ".yml" : String.valueOf(subFolder) + "/" + name + ".yml";
        this.plugin.saveResource(resource, replace);
    }
    
    /**
     * Reloads a config file.
     * 
     * @param name The name of the config.
     */
    public void reloadConfig(String name) {
        this.datas.remove(name);
    }
    
    /**
     * Creates a config with a special file extension instead of .yml
     * 
     * @param name Name of the file. make sure to include your file extension.
     * @param subFolder The sub folder to put the file in.
     * @return The bukkit FileConfiguration for the file.
     */
    public FileConfiguration customConfig(String name, String subFolder){
    	 File config = subFolder.equals("") ? new File(this.plugin.getDataFolder(), String.valueOf(name)) : new File(this.plugin.getDataFolder() + "/" + subFolder, String.valueOf(name));
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
    
    /**
     * Saves the file with the specified file extension.
     * 
     * @param name The name of the file with file extension.
     * @param subFolder The sub folder to put the file in.
     */
    public void saveCustomConfig(String name, String subFolder){
    	File config = subFolder.equals("") ? new File(this.plugin.getDataFolder(), String.valueOf(name)) : new File(this.plugin.getDataFolder() + "/" + subFolder, String.valueOf(name));
        try {
            this.plugin.getConfig().options().copyDefaults(true);
            this.config(name, subFolder).save(config);
            this.config(name, subFolder);
        }
        catch (IOException ex) {
            this.plugin.getLogger().log(Level.WARNING, "Couldn''t save {0}.yml", name);
        }
    }
    
    /**
     * Creates a normal file and return it.
     * 
     * @param name The name of the file including file extension.
     * @param subFolder The sub folder to put the file in.
     * @return The File.
     * @throws IOException we're writing to files. what did you expect?
     */
    public File file(String name, String subFolder) throws IOException{
    	File config = subFolder.equals("") ? new File(this.plugin.getDataFolder(), String.valueOf(name)) : new File(this.plugin.getDataFolder() + "/" + subFolder, String.valueOf(name));
    	if(!config.exists()){
    		config.createNewFile();
    	}
    	return config;
    }
}