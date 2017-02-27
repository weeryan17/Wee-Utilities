package com.weeryan17.dgs.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.weeryan17.dgs.DiscordGroups;

public class Storage {
	DiscordGroups instance;

	public Storage(DiscordGroups instance) {
		this.instance = instance;
	}

	/**
	 * get's the sheet for storing player data.
	 * 
	 * @return The player sheet.
	 */
	public Sheet getPlayerSheet() {
		try {
			FileInputStream in = new FileInputStream(instance.getJarLoc() + "/" + instance.getProperties().getProperty("workbookPath"));
			Workbook wb = new XSSFWorkbook(in);
			boolean sheetExists = false;
			Sheet sheet = null;
			for (int i = 0; i <= wb.getNumberOfSheets() - 1; i++) {
				Sheet rawSheet = wb.getSheetAt(i);
				String name = rawSheet.getSheetName();
				if (name.equals("Users")) {
					sheetExists = true;
					sheet = rawSheet;
				}
			}
			if (sheetExists) {
				wb.close();
				return sheet;
			} else {
				instance.getLogger().log("Players sheet wasn't found", Level.SEVERE, false);
				System.exit(1);
				wb.close();
				return null;
			}
		} catch (IOException e) {
			instance.getLogger().log("Chouldn't reed player data sheet", Level.SEVERE, e, false);
			System.exit(1);
			return null;
		}
	}

	/**
	 * get's the sheet for storing player data.
	 * 
	 * @return The player sheet.
	 */
	public Sheet getKeysSheet() {
		try {
			FileInputStream in = new FileInputStream(instance.getJarLoc() + "/" + instance.getProperties().getProperty("workbookPath"));
			Workbook wb = new XSSFWorkbook(in);
			boolean sheetExists = false;
			Sheet sheet = null;
			for (int i = 0; i <= wb.getNumberOfSheets() - 1; i++) {
				Sheet rawSheet = wb.getSheetAt(i);
				String name = rawSheet.getSheetName();
				if (name.equals("Keys")) {
					sheetExists = true;
					sheet = rawSheet;
				}
			}
			if (sheetExists) {
				wb.close();
				return sheet;
			} else {
				instance.getLogger().log("Keys sheet wasn't found", Level.SEVERE, false);
				System.exit(1);
				wb.close();
				return null;
			}
		} catch (IOException e) {
			instance.getLogger().log("Chouldn't reed keys data sheet", Level.SEVERE, e, false);
			System.exit(1);
			return null;
		}
	}
	
	/**
	 * Get's the permission sheet for the specified guild.
	 * 
	 * @param guildID The guild id to get a sheet for.
	 * @return The guild sheet.
	 */
	public Sheet getGuildSheet(String guildID){
		try {
			FileInputStream in = new FileInputStream(instance.getJarLoc() + "/" + instance.getProperties().getProperty("permsWorkbook"));
			Workbook wb = new XSSFWorkbook(in);
			boolean sheetExists = false;
			Sheet sheet = null;
			for (int i = 0; i <= wb.getNumberOfSheets() - 1; i++) {
				Sheet rawSheet = wb.getSheetAt(i);
				String name = rawSheet.getSheetName();
				if (name.equals(guildID)) {
					sheetExists = true;
					sheet = rawSheet;
				}
			}
			if (sheetExists) {
				wb.close();
				return sheet;
			} else {
				instance.getLogger().log("Guild sheet wasn't found for guild " + guildID + ". Going to try an make it.", Level.WARNING, false);
				sheet = wb.createSheet(guildID);
				savePermsWorkbook(wb);
				return sheet;
			}
		} catch (IOException e) {
			instance.getLogger().log("Chouldn't reed Guild data sheet for guild " + guildID, Level.SEVERE, e, false);
			System.exit(1);
			return null;
		}
	}
	
	protected static int saveCount = 0;
	
	/**
	 * Saves the main data workbook
	 * 
	 * @param wb The data workbook to be saved
	 */
	public void saveDataWorkbook(Workbook wb){
		FileOutputStream out;
		try {
			out = new FileOutputStream(instance.getJarLoc() + "/" + instance.getProperties().getProperty("workbookPath"));
			wb.write(out);
			saveCount = 0;
			out.close();
			wb.close();
		} catch (FileNotFoundException e) {
			instance.getLogger().log("Chouldn't save workbook because file wasn't found", Level.SEVERE, e, false);
			System.exit(1);
		} catch (IOException e) {
			if(saveCount >= 10){
				instance.getLogger().log("Chouldn't save workbook 10 times in a row.", Level.SEVERE, e, false);
				System.exit(1);
			} else {
				saveCount++;
				instance.getLogger().log("Chouldn't save file. Assuming file is open else where so retrying in 100 seconds. Retry #" + saveCount, Level.WARNING, e, false);
				new Timer().schedule(new TimerTask(){

					@Override
					public void run() {
						saveDataWorkbook(wb);
					}
					
				}, 100000L);
			}
		}
	}
	
	/**
	 * Saves the permission workbook.
	 * 
	 * @param wb The permission workbook to be saved.
	 */
	public void savePermsWorkbook(Workbook wb){
		FileOutputStream out;
		try {
			out = new FileOutputStream(instance.getJarLoc() + "/" + instance.getProperties().getProperty("permsWorkbook"));
			wb.write(out);
			saveCount = 0;
			out.close();
			wb.close();
		} catch (FileNotFoundException e) {
			instance.getLogger().log("Chouldn't save workbook because file wasn't found", Level.SEVERE, e, false);
			System.exit(1);
		} catch (IOException e) {
			if(saveCount >= 10){
				instance.getLogger().log("Chouldn't save workbook 10 times in a row.", Level.SEVERE, e, false);
				System.exit(1);
			} else {
				saveCount++;
				instance.getLogger().log("Chouldn't save file. Assuming file is open else where so retrying in 100 seconds. Retry #" + saveCount, Level.WARNING, e, false);
				new Timer().schedule(new TimerTask(){

					@Override
					public void run() {
						savePermsWorkbook(wb);
					}
					
				}, 100000L);
			}
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
