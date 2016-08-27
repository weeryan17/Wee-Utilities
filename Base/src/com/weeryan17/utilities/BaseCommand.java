package com.weeryan17.utilities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.weeryan17.utilities.api.CommandApi;
import com.weeryan17.utilities.api.PluginMannager;
/**
 * Command for this plugin
 * 
 * @author weeryan17
 *
 */
public class BaseCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
        if (args.length >= 1) {
            String string = args[0].toLowerCase();
            switch (string.toLowerCase()) {
                case "help": {
                    this.help(sender);
                }
                break;
                case "reload":{
                	for(JavaPlugin plugin : PluginMannager.plugins){
                		Bukkit.getServer().getPluginManager().disablePlugin(plugin);
                		//Bukkit.getServer().getPluginManager().enablePlugin(plugin);
                	}
                }
                break;
                default: {
                	
                }
            }
        } else {
            this.help(sender);
        }
        return false;
    }

    public void help(CommandSender sender) {
        sender.sendMessage((Object)ChatColor.BLUE + "Wee Utilitys help");
        sender.sendMessage((Object)ChatColor.BLUE + "/wu help: opens up this help menu");
        String[][] commands = CommandApi.list;
        int max = commands.length - 1;
        for(int i = 0; i <= max; i++){
            String[] list = commands[i];
            if (list[3] == null || sender.hasPermission(list[3])) {
                sender.sendMessage((Object)ChatColor.BLUE + list[1] + ": " + list[2] + " The plugin this command is from is " + (Object)ChatColor.RED + list[0]);
            }
        }
    }
}