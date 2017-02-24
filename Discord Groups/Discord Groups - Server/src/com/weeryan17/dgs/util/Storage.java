package com.weeryan17.dgs.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

import org.apache.poi.xssf.usermodel.XSSFSheet;
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
	public XSSFSheet getPlayerSheet() {
		try {
			XSSFWorkbook wb = new XSSFWorkbook(instance.getProperties().getProperty("workbookPath"));
			boolean sheetExists = false;
			XSSFSheet sheet = null;
			for (int i = 0; i <= wb.getNumberOfSheets() - 1; i++) {
				XSSFSheet rawSheet = wb.getSheetAt(i);
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
	public XSSFSheet getKeysSheet() {
		try {
			XSSFWorkbook wb = new XSSFWorkbook(instance.getProperties().getProperty("workbookPath"));
			boolean sheetExists = false;
			XSSFSheet sheet = null;
			for (int i = 0; i <= wb.getNumberOfSheets() - 1; i++) {
				XSSFSheet rawSheet = wb.getSheetAt(i);
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

	public void logResult(String result) {
		File file = null;
		try {
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("MM.dd.yy.HH.mm.ss.SSS");
			String time = format.format(date);
			String folder = instance.getProperties().getProperty("resultFolder");
			File dir = new File(folder);
			dir.mkdirs();
			file = new File(folder + "/" + time + ".result");
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
