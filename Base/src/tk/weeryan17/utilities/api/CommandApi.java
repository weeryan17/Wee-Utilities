package tk.weeryan17.utilities.api;

import org.bukkit.plugin.java.JavaPlugin;

import tk.weeryan17.utilities.api.interfaces.BaseCommand;
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
            String[][] arrstring = list;
            int i = -1;
            list = new String[length + 1][4];
            int max = arrstring.length - 1;
            for(i = 0; i <= max; i++){
                String[] stored = arrstring[i];
                CommandApi.list[i] = stored;
            }
            CommandApi.list[i] = new String[]{pluginName, command, discrition, permission};
        }
    }
    
    /**
     * Registers your command so it show up in Wee Utilities command list.
     * 
     * @param id You plugin id gotten from PluginMannager.
     * @param command The command to register.
     * @param discrition Your command description.
     * @param permission The permission required to perform this command.
     * 
     * @deprecated Just send us your command. It will handle everything from their.
     */
    @Deprecated
    public void registerCommand(int id, String command, String discrition, String permission) {
        JavaPlugin plugin = PluginMannager.plugins.get(id);
        String pluginName = plugin.getName();
        if (list == null) {
            list = new String[1][4];
            CommandApi.list[0] = new String[]{pluginName, command, discrition, permission};
        } else {
            int length = list.length;
            String[][] arrstring = list;
            int i = -1;
            list = new String[length + 1][4];
            int max = arrstring.length - 1;
            for(i = 0; i <= max; i++){
                String[] stored = arrstring[i];
                CommandApi.list[i] = stored;
            }
            CommandApi.list[i] = new String[]{pluginName, command, discrition, permission};
        }
    }
    
    public void registerCommand(BaseCommand command){
    	JavaPlugin plugin = command.getPlugin();
    	plugin.getLogger().info("Registering the command " + command.getCommand() + " with Wee-Utilities");
    	plugin.getCommand(command.getCommand()).setExecutor(command);
        String pluginName = plugin.getName();
        if (list == null) {
            list = new String[1][4];
            CommandApi.list[0] = new String[]{pluginName, command.getCommand(), command.getDescription(), command.getPermission()};
        } else {
            int length = list.length;
            String[][] arrstring = list;
            int i = -1;
            list = new String[length + 1][4];
            int max = arrstring.length - 1;
            for(i = 0; i <= max; i++){
                String[] stored = arrstring[i];
                CommandApi.list[i] = stored;
            }
            CommandApi.list[i] = new String[]{pluginName, command.getCommand(), command.getDescription(), command.getPermission()};
        }
    }
}
