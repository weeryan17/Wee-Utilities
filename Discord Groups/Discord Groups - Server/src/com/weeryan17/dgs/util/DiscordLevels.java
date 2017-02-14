package com.weeryan17.dgs.util;

import java.util.logging.Level;

@SuppressWarnings("serial")
public class DiscordLevels extends Level {
	public static final Level DiscordRateLimit = new DiscordLevels("DISCORD_RATE_LIMIT", Level.SEVERE.intValue() + 1);
	public static final Level DiscordMissingPerms = new DiscordLevels("DISCORD_MISSING_PERMS", Level.SEVERE.intValue() + 2);
	public static final Level Discord = new DiscordLevels("DISCORD", Level.SEVERE.intValue());
	
	protected DiscordLevels(String name, int value) {
		super(name, value);
	}

}
