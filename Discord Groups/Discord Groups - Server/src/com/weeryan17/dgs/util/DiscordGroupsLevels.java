package com.weeryan17.dgs.util;

import java.util.logging.Level;

public class DiscordGroupsLevels extends Level {
	public static final Level DiscordLog = new DiscordGroupsLevels("DiscordLog", 7);
	
	
	private static final long serialVersionUID = 1L;

	protected DiscordGroupsLevels(String name, int value) {
		super(name, value);
	}

}
