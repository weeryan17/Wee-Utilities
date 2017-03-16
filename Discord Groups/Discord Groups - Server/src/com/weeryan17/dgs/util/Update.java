package com.weeryan17.dgs.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;

import com.weeryan17.dgs.DiscordGroups;

public class Update {
	DiscordGroups instance;

	public Update(DiscordGroups instance) {
		this.instance = instance;
	}

	/**
	 * Updates the bot.
	 * 
	 * Borrowed most of this from FlareBot.
	 */
	public void update() {
		File git = new File("Wee-Utilities" + File.separator);
		if (!git.exists() || git.isDirectory()) {
			ProcessBuilder clone = new ProcessBuilder("git", "clone", "https://github.com/weeryan17/Wee-Utilities", "--branch", "discord-groups");
			clone.redirectErrorStream(true);
			Process p = null;
			try {
				p = clone.start();
			} catch (IOException e) {
				instance.getLogger().log("Chouldn't update", Level.WARNING, e, true);
			}

			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while (p.isAlive()) {

			}
			try {
				if ((line = reader.readLine()) != null) {
					sb.append(line + '\n');
				}
			} catch (IOException e) {
				instance.getLogger().log("Can't read clone output", Level.WARNING, e, true);
			}

			if (p.exitValue() != 0) {
				instance.getLogger().log("Clone exited with a non 0 status\n" + sb.toString(), Level.WARNING, true);
			}
		} else {
			ProcessBuilder pull = new ProcessBuilder("git", "pull");
			pull.directory(git);
			Process p = null;
			try {
				p = pull.start();
			} catch (IOException e) {
				instance.getLogger().log("Chouldn't update", Level.WARNING, e, true);
			}

			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while (p.isAlive()) {

			}
			try {
				if ((line = reader.readLine()) != null) {
					sb.append(line + '\n');
				}
			} catch (IOException e) {
				instance.getLogger().log("Can't read pull output", Level.WARNING, e, true);
			}

			if (p.exitValue() != 0) {
				instance.getLogger().log("Pull exited with a non 0 status\n" + sb.toString(), Level.WARNING, true);
			}
		}
		ProcessBuilder maven = new ProcessBuilder("nvm", "clean", "compile", "assembly:single");
		File mvn = new File("Wee-Utilities/Discord Groups - Server" + File.separator);
		maven.directory(mvn);
		Process p = null;
		try {
			p = maven.start();
		} catch (IOException e1) {
			instance.getLogger().log("Error compiling project", Level.WARNING, e1, true);
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while (p.isAlive()) {

		}
		try {
			if ((line = reader.readLine()) != null) {
				sb.append(line + '\n');
			}
		} catch (IOException e) {
			instance.getLogger().log("Can't compile project", Level.WARNING, e, true);
		}
		if (p.exitValue() != 0) {
			instance.getLogger().log("Compiling project exited with a non 0 status\n" + sb.toString(), Level.WARNING,
					true);
		}
	}
}
