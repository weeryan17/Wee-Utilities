package com.weeryan17.dgs.listeners;

import java.util.logging.Level;

import com.weeryan17.dgs.DiscordGroups;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class LogListener extends Filter<ILoggingEvent> {
	DiscordGroups instance;
	public LogListener(){
		this.instance = DiscordGroups.getStaticInstance();
	}
	@Override
	public FilterReply decide(ILoggingEvent e) {
		instance.getLogger().log(e.getFormattedMessage(), Level.parse(e.getLevel().toString()), false);
		return FilterReply.NEUTRAL;
	}

}
