package com.weeryan17.utilities.api;

import com.weeryan17.utilities.PluginMannager;
import org.bukkit.plugin.java.JavaPlugin;
/**
 * Used for command integration.
 * 
 * @author weeryan17
 *
 */
public class CommandApi {
    public static String[][] list;

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
