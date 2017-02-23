package com.weeryan17.dgs.util;

import java.awt.TrayIcon.MessageType;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.weeryan17.dgs.DiscordGroups;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class Logging {
	
	DiscordGroups instance;
	
	IChannel logChannel;
	
	Logger logger;
	
	public Logging(DiscordGroups instance){
		this.instance = instance;
		String channelId = "280177651392708608";
		logChannel = instance.getMainGuild().getChannelByID(channelId);
		logger = Logger.getLogger("com.weeryan17");
	}
	
	/**
	 * Logs stuff. both to console and file.
	 * Optionally logs to discord
	 * 
	 * @param message The message to log.
	 * @param discord If you want to log to discord.
	 */
	
	public void log(String message, boolean discord){
		if(discord){
			try {
				logChannel.sendMessage(message);
			} catch (MissingPermissionsException e) {
				this.log("Discord bot is missing permisions", DiscordLevels.DiscordMissingPerms, e, false);
			} catch (RateLimitException e) {
				this.log("Discord bot hit it's rate limit", DiscordLevels.DiscordRateLimit, e, false);
			} catch (DiscordException e) {
				this.log("Discord bot ran into a error", DiscordLevels.Discord, e, false);
			}	
		}
		
		Date dateobj = new Date();
		
		DateFormat dayf = new SimpleDateFormat("MM.dd.yy");
		String dayDate = dayf.format(dateobj);
		
		DateFormat hourf = new SimpleDateFormat("HH");
		String hourDate = hourf.format(dateobj) + ".00";
		
		DateFormat secoondsf = new SimpleDateFormat("HH:mm:ss:SSS");
		String secondsDate = secoondsf.format(dateobj);
		
		BufferedWriter bw = null;
		FileWriter fw = null;
		
		File file = null;
		
		try {
			File dir = new File("C:/Users/developer/Documents/Logs/" + dayDate);
			dir.mkdirs();
			file = new File("C:/Users/developer/Documents/Logs/" + dayDate + "/" + hourDate + ".log");
			if(!file.exists()){
				file.createNewFile();
			}
			
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			
			bw.write("[" + secondsDate + "] " + message);
			bw.write('\n');
			
			bw.flush();
		} catch (IOException e) {
			this.log("Ran into a io expresion when writting to file " + file.getPath() + "\n" + 
					"Softly shutting down to prevent further problems", Level.SEVERE, e, false);
		}
		System.out.println(secondsDate + " [DiscordGroups] " + message);
	}
	
	/**
	 * Logs stuff with a log level. Both to console and file.
	 * Optionally logs to discord.
	 * 
	 * @param message The message to log.
	 * @param level The level at witch to log it.
	 * @param discord If you want to log to discord.
	 */
	public void log(String message, Level level, boolean discord){
		if(discord){
			try {
				logChannel.sendMessage("[" + level.getName() + "] " + message);
			} catch (MissingPermissionsException e) {
				this.log("Discord bot is missing permisions", DiscordLevels.DiscordMissingPerms, e, false);
			} catch (RateLimitException e) {
				this.log("Discord bot hit it's rate limit", DiscordLevels.DiscordRateLimit, e, false);
			} catch (DiscordException e) {
				this.log("Discord bot ran into a error", DiscordLevels.Discord, e, false);
			}
		}
		
		Date dateobj = new Date();
		
		DateFormat dayf = new SimpleDateFormat("MM.dd.yy");
		String dayDate = dayf.format(dateobj);
		
		DateFormat hourf = new SimpleDateFormat("HH");
		String hourDate = hourf.format(dateobj) + ".00";
		
		DateFormat secoondsf = new SimpleDateFormat("HH:mm:ss:SSS");
		String secondsDate = secoondsf.format(dateobj);
		
		BufferedWriter bw = null;
		FileWriter fw = null;
		
		File file = null;
		
		try {
			File dir = new File("C:/Users/developer/Documents/Logs/" + dayDate);
			dir.mkdirs();
			file = new File("C:/Users/developer/Documents/Logs/" + dayDate + "/" + hourDate + ".log");
			if(!file.exists()){
				file.createNewFile();
			}
			
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			
			bw.write("[" + secondsDate + "] " + "[" + level.getName() + "] " + message);
			bw.write('\n');
			
			bw.flush();
		} catch (IOException e) {
			this.log("Ran into a io expresion when writting to file " + file.getPath() + "\n" + 
					"Softly shutting down to prevent further problems", Level.SEVERE, e, false);
		}
		logger.log(level, secondsDate + " [DiscordGroups] " + message);
	}
	
	/**
	 * Logs stuff with a log level, and a Throwable. Both to console and file.
	 * Optionally logs to discord.
	 * 
	 * @param message The message to log.
	 * @param level The level at witch to log it.
	 * @param thrown The exception thrown.
	 * @param discord If you want to log to discord.
	 */
	public void log(String message, Level level, Throwable thrown, boolean discord){
		if(discord){
			try {
				logChannel.sendMessage("[" + level.getName() + "] " + message);
			} catch (MissingPermissionsException e) {
				this.log("Discord bot is missing permisions", DiscordLevels.DiscordMissingPerms, e, false);
			} catch (RateLimitException e) {
				this.log("Discord bot hit it's rate limit", DiscordLevels.DiscordRateLimit, e, false);
			} catch (DiscordException e) {
				this.log("Discord bot ran into a error", DiscordLevels.Discord, e, false);
			}
		}
		
		Date dateobj = new Date();
		
		DateFormat dayf = new SimpleDateFormat("MM.dd.yy");
		String dayDate = dayf.format(dateobj);
		
		DateFormat hourf = new SimpleDateFormat("HH");
		String hourDate = hourf.format(dateobj) + ".00";
		
		DateFormat secoondsf = new SimpleDateFormat("HH:mm:ss:SSS");
		String secondsDate = secoondsf.format(dateobj);
		
		BufferedWriter bw = null;
		FileWriter fw = null;
		
		File file = null;
		
		try {
			File dir = new File("C:/Users/developer/Documents/Logs/" + dayDate);
			dir.mkdirs();
			file = new File("C:/Users/developer/Documents/Logs/" + dayDate + "/" + hourDate + ".log");
			if(!file.exists()){
				file.createNewFile();
			}
			
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			
			bw.write("[" + secondsDate + "] " + "[" + level.getName() + "] " + message);
			bw.write('\n');
			
			bw.flush();
		} catch (IOException e) {
			this.log("Ran into a io expresion when writting to file " + file.getPath() + "\n" + 
					"Softly shutting down to prevent further problems", Level.SEVERE, e, false);
		}
		if(DiscordGroups.hasTray){
			instance.getIcon().displayMessage("Discord Groups", message, MessageType.ERROR);
		}
		logger.log(level, secondsDate + " [DiscordGroups] " + message, thrown);
	}
	
}
