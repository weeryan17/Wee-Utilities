package com.weeryan17.utilities.api;

/**
 * Used to easily reference the minecraft formating codes
 * 
 * @author weeryan17
 *
 */
public enum MinecraftColor {
	/**
	 * Represents the color black in minecraft formatting terms.
	 */
	BLACK("§0"),
	
	/**
	 * Represents the color dark blue in minecraft formatting terms.
	 */
	DARK_BLUE("§1"),
	
	/**
	 * Represents the color dark green in minecraft formatting terms.
	 */
	DARK_GREEN("§2"),

	/**
	 * Represents the color dark aqua in minecraft formatting terms.
	 */
	DARK_AQUA("§3"),

	/**
	 * Represents the color dark red in minecraft formatting terms.
	 */
	DARK_RED("§4"),

	/**
	 * Represents the color dark purple in minecraft formatting terms.
	 */
	DARK_PURPLE("§5"),

	/**
	 * Represents the color gold in minecraft formatting terms.
	 */
	GOLD("§6"),

	/**
	 * Represents the color gray in minecraft formatting terms.
	 */
	GRAY("§7"),

	/**
	 * Represents the color dark gray in minecraft formatting terms.
	 */
	DARK_GRAY("§8"),

	/**
	 * Represents the color blue in minecraft formatting terms.
	 */
	BLUE("§9"),

	/**
	 * Represents the color green in minecraft formatting terms.
	 */
	GREEN("§a"),

	/**
	 * Represents the color aqua in minecraft formatting terms.
	 */
	AQUA("§b"),

	/**
	 * Represents the color red in minecraft formatting terms.
	 */
	RED("§c"),

	/**
	 * Represents the color light purple in minecraft formatting terms.
	 */
	LIGHT_PURPLE("§d"),

	/**
	 * Represents the color yellow in minecraft formatting terms.
	 */
	YELLOW("§e"),

	/**
	 * Represents the color white in minecraft formatting terms.
	 */
	WHITE("§f");
	
	private String color;
	private MinecraftColor(String color){
		this.color = color;
	}
	
	/**
	 * Gets weather or not a string is a color
	 * 
	 * @param string The string you want to check
	 * @return a boolean representing if it's a color
	 */
	public static boolean isColor(String string){
		boolean color = false;
		for(MinecraftColor m : MinecraftColor.values()){
			String s = m + "";
			if(s.equals(string)){
				color = true;
			}
		}
		return color;
	}
	
};
