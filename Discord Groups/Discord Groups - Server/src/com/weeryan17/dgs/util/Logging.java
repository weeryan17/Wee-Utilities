package com.weeryan17.dgs.util;

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
	
	long channelId;

	Logger logger;

	String logFolder;

	public Logging(DiscordGroups instance) {
		this.instance = instance;
		channelId = 280177651392708608L;
		logger = Logger.getLogger("com.weeryan17");
		logFolder = instance.getProperties().getProperty("logFolder");
	}

	/**
	 * Logs stuff. both to console and file. Optionally logs to discord
	 * 
	 * @param message
	 *            The message to log.
	 * @param discord
	 *            If you want to log to discord.
	 */

	public void log(String message, boolean discord) {
		if (discord) {
			sendMessage(message);
		}

		Date dateobj = new Date();

		DateFormat secoondsf = new SimpleDateFormat("HH:mm:ss:SSS");
		String secondsDate = secoondsf.format(dateobj);

		System.out.println(secondsDate + " [DiscordGroups] " + message);
		
		this.writeToFile(message);
	}

	/**
	 * Logs stuff with a log level. Both to console and file. Optionally logs to
	 * discord.
	 * 
	 * @param message
	 *            The message to log.
	 * @param level
	 *            The level at witch to log it.
	 * @param discord
	 *            If you want to log to discord.
	 */
	public void log(String message, Level level, boolean discord) {
		if (discord) {
			sendMessage("[" + level.getName() + "] " + message);
		}

		Date dateobj = new Date();

		DateFormat secoondsf = new SimpleDateFormat("HH:mm:ss:SSS");
		String secondsDate = secoondsf.format(dateobj);

		writeToFile("[" + level.getName() + "] " + message);

		logger.log(level, secondsDate + " [DiscordGroups] " + message);
	}

	/**
	 * Logs stuff with a log level, and a Throwable. Both to console and file.
	 * Optionally logs to discord.
	 * 
	 * @param message
	 *            The message to log.
	 * @param level
	 *            The level at witch to log it.
	 * @param thrown
	 *            The exception thrown.
	 * @param discord
	 *            If you want to log to discord.
	 */
	public void log(String message, Level level, Throwable thrown, boolean discord) {
		if (discord) {
			sendMessage("[" + level.getName() + "] " + message);
		}

		Date dateobj = new Date();

		DateFormat secoondsf = new SimpleDateFormat("HH:mm:ss:SSS");
		String secondsDate = secoondsf.format(dateobj);

		StringBuilder sb = new StringBuilder();

		sb.append("[" + secondsDate + "] " + "[" + level.getName() + "] " + message);
		sb.append('\n');
		sb.append(thrown.getMessage());
		sb.append('\n');
		for (StackTraceElement element : thrown.getStackTrace()) {
			sb.append(element.getClassName() + " class generated an error on line " + element.getLineNumber()
					+ " in the method " + element.getMethodName() + "().");
			sb.append("\n");
		}

		writeToFile(sb.toString());

		logger.log(level, secondsDate + " [DiscordGroups] " + message, thrown);
	}

	public void sendMessage(String message) {
		try {
			if(logChannel == null){
				logChannel = instance.getMainGuild().getChannelByID(channelId);
			}
			logChannel.sendMessage(message);
		} catch (MissingPermissionsException e) {
			this.log("Discord bot is missing permisions", Level.WARNING, e, false);
		} catch (RateLimitException e) {
			this.log("Discord bot hit it's rate limit", Level.SEVERE, e, false);
		} catch (DiscordException e) {
			this.log("Discord bot ran into a error", Level.WARNING, e, false);
		}
	}

	public void writeToFile(String message) {
		Date dateobj = new Date();

		DateFormat dayf = new SimpleDateFormat("yyyy-MM-dd");
		String dayDate = dayf.format(dateobj);

		DateFormat hourf = new SimpleDateFormat("HH");
		String hourDate = hourf.format(dateobj) + ".00";

		DateFormat secoondsf = new SimpleDateFormat("HH:mm:ss:SSS");
		String secondsDate = secoondsf.format(dateobj);

		BufferedWriter bw = null;
		FileWriter fw = null;

		File file = null;

		try {
			File dir = new File(instance.getJarLoc() + "/" + logFolder + "/" + dayDate);
			dir.mkdirs();
			file = new File(instance.getJarLoc() + "/" + logFolder + "/" + dayDate + "/" + hourDate + ".log");
			if (!file.exists()) {
				file.createNewFile();
			}

			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);

			bw.write("[" + secondsDate + "] " + message);
			bw.write('\n');

			bw.flush();
		} catch (IOException e) {
			this.log("Ran into a io expresion when writting to file " + file.getPath() + "\n"
					+ "Softly shutting down to prevent further problems", Level.SEVERE, e, false);
		}
	}
}
