package com.weeryan17.utilities.api.interfaces;

import org.bukkit.command.CommandExecutor;

public interface BaseCommand extends CommandExecutor {
	/**
	 * Gets the command usage.
	 * 
	 * @return The command usage.
	 */
	String getUsage();
}
