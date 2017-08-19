package tk.weeryan17.utilities.api.interfaces;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public interface BaseCommand extends CommandExecutor {
	/**
	 * Gets the command usage.
	 * 
	 * @return The command usage.
	 */
	String getUsage();
	
	/**
	 * Gets the command string
	 * 
	 * @return The command string
	 */
	String getCommand();
	
	/**
	 * Get's the basic description for the command
	 * @return The description
	 */
	String getDescription();
	
	/**
	 * Get's the JavaPlugin. useful for well everything.
	 * 
	 * @return The JavaPlugin
	 */
	JavaPlugin getPlugin();
	
	/**
	 * Get's the permission assigned to this command.
	 * 
	 * @return The permission
	 */
	default String getPermission(){
		return getPlugin().getName() + "." + getCommand();
	}
	
	default void sendUsage(Player p){
		p.sendMessage(ChatColor.BLUE + getPlugin().getName() + " plugin usage\n"
				+ getUsage());
	}
}
