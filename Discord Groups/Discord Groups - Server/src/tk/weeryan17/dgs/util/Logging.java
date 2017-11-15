package tk.weeryan17.dgs.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.sentry.Sentry;
import io.sentry.event.EventBuilder;
import io.sentry.event.interfaces.ExceptionInterface;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.EmbedBuilder;
import tk.weeryan17.dgs.DiscordGroups;

public class Logging {

	DiscordGroups instance;

	IChannel logChannel;

	Long channelId;

	Logger logger;

	String logFolder;
	
	String space = "                 ";

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
			EmbedBuilder eb = new EmbedBuilder();
			eb.withTitle("");
			sendMessage("[" + level.getName() + "] " + message);
		}

		Date dateobj = new Date();

		DateFormat secoondsf = new SimpleDateFormat("HH:mm:ss:SSS");
		String secondsDate = secoondsf.format(dateobj);

		writeToFile("[" + level.getName() + "] " + message);

		logger.log(level, secondsDate + " [DiscordGroups] " + message);
		
		if(level.equals(Level.SEVERE)) {
			EventBuilder eventBuilder = new EventBuilder()
                    .withMessage(message)
                    .withLevel(io.sentry.event.Event.Level.ERROR)
                    .withLogger(Logger.class.getName());
			
			Sentry.capture(eventBuilder.build());
		}
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
		
		if(level.equals(Level.SEVERE)) {
			EventBuilder eventBuilder = new EventBuilder()
                    .withMessage(message)
                    .withLevel(io.sentry.event.Event.Level.ERROR)
                    .withLogger(Logger.class.getName())
                    .withSentryInterface(new ExceptionInterface(thrown));
			
			Sentry.capture(eventBuilder.build());
		}
	}
	
	/**
	 * Logs an embed.
	 * Will always log to discord for obvious reasons.
	 * 
	 * @param embed The embed.
	 */
	public void logEmbed(EmbedObject embed){
		StringBuilder embedString = new StringBuilder();
		embedString.append(embed.title + "\n");
		embedString.append(space + embed.description + "\n");
		for(EmbedObject.EmbedFieldObject field: embed.fields){
			embedString.append(space + field.name + "\n");
			embedString.append(space + space + field.value + "\n");
		}
	}

	public void sendMessage(String message) {
		if (logChannel == null) {
			logChannel = instance.getMainGuild().getChannelByID(channelId);
		}
		logChannel.sendMessage(message);
	}
	
	public void sendEmbed(EmbedObject embed){
		if (logChannel == null) {
			logChannel = instance.getMainGuild().getChannelByID(channelId);
		}
		logChannel.sendMessage(embed);
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
	
	public void logResult(String result) {
		File file = null;
		try {
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("MM.dd.yy.HH.mm.ss.SSS");
			String time = format.format(date);
			String folder = instance.getProperties().getProperty("resultFolder");
			File dir = new File(folder);
			dir.mkdirs();
			file = new File(instance.getJarLoc() + "/" + folder + "/" + time + ".result");
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(result);

			bw.flush();
			bw.close();
		} catch (IOException e) {
			instance.getLogger().log("Ran into a io exception when writting to result file", Level.WARNING, e, false);
		}
	}
}
