package com.weeryan17.utilities.api.interfaces;

public interface SubCommand extends BaseCommand {
	String[] subArgs();
	
	/**
	 * Returns a sub usage for working with when you have sub commands.
	 * Format works the same as the main getUsage
	 * 
	 * @return The sub usage
	 */
	default String getSubUsage() {
		return getUsage();
	}
}
