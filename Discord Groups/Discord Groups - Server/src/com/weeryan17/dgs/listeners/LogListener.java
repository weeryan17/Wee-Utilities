package com.weeryan17.dgs.listeners;

import com.weeryan17.dgs.DiscordGroups;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class LogListener extends Filter<ILoggingEvent> {
	DiscordGroups instance;
	public LogListener(DiscordGroups instance){
		this.instance = instance;
	}
	@Override
	public FilterReply decide(ILoggingEvent e) {
		instance.getLogger().log("Message: " + e.getMessage(), false);
		instance.getLogger().log("Formated Message: " + e.getFormattedMessage(), false);
		instance.getLogger().log("Level: " + e.getLevel(), false);
		return null;
	}

}
