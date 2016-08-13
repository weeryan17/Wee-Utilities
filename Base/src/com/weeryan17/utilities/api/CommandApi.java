package com.weeryan17.utilities.api;

import org.bukkit.plugin.java.JavaPlugin;
/**
 * Used for command integration.
 * 
 * @author weeryan17
 *
 */
public class CommandApi {
    public static String[][] list;
    /**
     * Registers your command so it show up in Wee Utilities command list.
     * 
     * @param plugin Your plugin.
     * @param command The command to register.
     * @param discrition Your command description.
     * @param permission The permission required to perform this command.
     * 
     * @deprecated use your plugin id instead. Your plugin id is gotten from PluginMannager.
     */
    @Deprecated
    public void registerCommand(JavaPlugin plugin, String command, String discrition, String permission) {
        String pluginName = plugin.getName();
        if (list == null) {
            list = new String[1][4];
            CommandApi.list[0] = new String[]{pluginName, command, discrition, permission};
        } else {
            int length = list.length;
            String[][] temp = list;
            int i = -1;
            list = new String[length + 1][4];
            String[][] arrstring = temp;
            int max = arrstring.length;
            for(i = 0; i <= max; i++){
                String[] stored = arrstring[i];
                CommandApi.list[i + 1] = stored;
            }
            CommandApi.list[i + 1] = new String[]{pluginName, command, discrition, permission};
        }
    }
    /**
     * Registers your command so it show up in Wee Utilities command list.
     * 
     * @param id You plugin id gotten from PluginMannager.
     * @param command The command to register.
     * @param discrition Your command description.
     * @param permission The permission required to perform this command.
     */
    public void registerCommand(int id, String command, String discrition, String permission) {
        JavaPlugin plugin = PluginMannager.plugins.get(id);
        String pluginName = plugin.getName();
        if (list == null) {
            list = new String[1][4];
            CommandApi.list[0] = new String[]{pluginName, command, discrition, permission};
        } else {
            int length = list.length;
            String[][] temp = list;
            int i = -1;
            list = new String[length + 1][4];
            String[][] arrstring = temp;
            int max = arrstring.length;
            for(i = 0; i <= max; i++){
                String[] stored = arrstring[i];
                CommandApi.list[i + 1] = stored;
            }
            CommandApi.list[i + 1] = new String[]{pluginName, command, discrition, permission};
        }
    }
}
