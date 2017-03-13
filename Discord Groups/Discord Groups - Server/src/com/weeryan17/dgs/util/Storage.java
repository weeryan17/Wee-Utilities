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
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.weeryan17.dgs.DiscordGroups;

public class Storage {
	DiscordGroups instance;

	ArrayList<String> chars;

	public Storage(DiscordGroups instance) {
		this.instance = instance;
		chars = new ArrayList<String>();
		for (int i = 0; i <= 9; i++) {
			chars.add(String.valueOf(i));
		}
		chars.add("q");
		chars.add("w");
		chars.add("e");
		chars.add("r");
		chars.add("t");
		chars.add("y");
		chars.add("u");
		chars.add("i");
		chars.add("o");
		chars.add("p");
		chars.add("-");
		chars.add("a");
		chars.add("s");
		chars.add("d");
		chars.add("f");
		chars.add("g");
		chars.add("h");
		chars.add("j");
		chars.add("k");
		chars.add("l");
		chars.add(".");
		chars.add("z");
		chars.add("x");
		chars.add("c");
		chars.add("v");
		chars.add("b");
		chars.add("n");
		chars.add("m");
	}

	/**
	 * get's the sheet for storing player data.
	 * 
	 * @return The player sheet.
	 */
	@SuppressWarnings("resource")
	public Sheet getPlayerSheet() {
		try {
			FileInputStream in = new FileInputStream(
					instance.getJarLoc() + "/" + instance.getProperties().getProperty("workbookPath"));
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
	@SuppressWarnings("resource")
	public Sheet getKeysSheet() {
		try {
			FileInputStream in = new FileInputStream(
					instance.getJarLoc() + "/" + instance.getProperties().getProperty("workbookPath"));
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
	 * Get's the permission user sheet for the specified guild.
	 * 
	 * @param guildID
	 *            The guild id to get a sheet for.
	 * @return The guild sheet.
	 */
	@SuppressWarnings("resource")
	public Sheet getGuildUserSheet(String guildID) {
		try {
			FileInputStream in = new FileInputStream(
					instance.getJarLoc() + "/" + instance.getProperties().getProperty("permsWorkbook"));
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
				return sheet;
			} else {
				instance.getLogger().log("Guild sheet wasn't found for guild " + guildID + ". Going to try an make it.",
						Level.WARNING, false);
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

	/**
	 * Get's the permission role sheet for the specified guild.
	 * 
	 * @param guildID
	 *            The guild id to get a sheet for.
	 * @return The guild sheet.
	 */
	@SuppressWarnings("resource")
	public Sheet getGuildRoleSheet(String guildID) {
		try {
			FileInputStream in = new FileInputStream(
					instance.getJarLoc() + "/" + instance.getProperties().getProperty("permsWorkbook"));
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
				return sheet;
			} else {
				instance.getLogger().log("Guild sheet wasn't found for guild " + guildID + ". Going to try an make it.",
						Level.WARNING, false);
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
	 * @param wb
	 *            The data workbook to be saved
	 */
	public void saveDataWorkbook(Workbook wb) {
		FileOutputStream out;
		try {
			out = new FileOutputStream(
					instance.getJarLoc() + "/" + instance.getProperties().getProperty("workbookPath"));
			wb.write(out);
			saveCount = 0;
			out.close();
			wb.close();
		} catch (FileNotFoundException e) {
			instance.getLogger().log("Chouldn't save workbook because file wasn't found", Level.SEVERE, e, false);
			System.exit(1);
		} catch (IOException e) {
			if (saveCount >= 10) {
				instance.getLogger().log("Chouldn't save workbook 10 times in a row.", Level.SEVERE, e, false);
				System.exit(1);
			} else {
				saveCount++;
				instance.getLogger()
						.log("Chouldn't save file. Assuming file is open else where so retrying in 100 seconds. Retry #"
								+ saveCount, Level.WARNING, e, false);
				new Timer().schedule(new TimerTask() {

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
	 * @param wb
	 *            The permission workbook to be saved.
	 */
	public void savePermsWorkbook(Workbook wb) {
		FileOutputStream out;
		try {
			out = new FileOutputStream(
					instance.getJarLoc() + "/" + instance.getProperties().getProperty("permsWorkbook"));
			wb.write(out);
			saveCount = 0;
			out.close();
			wb.close();
		} catch (FileNotFoundException e) {
			instance.getLogger().log("Chouldn't save workbook because file wasn't found", Level.SEVERE, e, false);
			System.exit(1);
		} catch (IOException e) {
			if (saveCount >= 10) {
				instance.getLogger().log("Chouldn't save workbook 10 times in a row.", Level.SEVERE, e, false);
				System.exit(1);
			} else {
				saveCount++;
				instance.getLogger()
						.log("Chouldn't save file. Assuming file is open else where so retrying in 100 seconds. Retry #"
								+ saveCount, Level.WARNING, e, false);
				new Timer().schedule(new TimerTask() {

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

	public String getUserIDFromSpigot(String UUID) {
		Sheet users = this.getPlayerSheet();
		Row row = users.getRow(users.getFirstRowNum());
		String discordID = "";
		for (Cell cell : row) {
			if (cell.getCellTypeEnum().equals(CellType.STRING)) {
				if (cell.getStringCellValue().equals(UUID)) {
					int collum = cell.getColumnIndex();
					Row discord = users.getRow(users.getFirstRowNum() + 1);
					Cell userIDCell = discord.getCell(collum);
					discordID = userIDCell.getStringCellValue();
				}
			}
		}
		return discordID;
	}

	public String generateRandomID(String guildID) {
		Sheet keysSheet = this.getKeysSheet();
		Row keysRow = keysSheet.getRow(keysSheet.getFirstRowNum());
		ArrayList<String> keys = new ArrayList<String>();
		for (Cell cell : keysRow) {
			if (cell.getCellTypeEnum().equals(CellType.STRING)) {
				keys.add(cell.getStringCellValue());
			}
		}
		String key = this.randomID();
		if (!keys.contains(key)) {
			Cell cell = keysRow.createCell(keysRow.getPhysicalNumberOfCells() + 1);
			cell.setCellValue(key);
			Row idRow = keysSheet.getRow(keysSheet.getFirstRowNum() + 1);
			idRow.createCell(cell.getColumnIndex()).setCellValue(guildID);
			this.saveDataWorkbook(keysSheet.getWorkbook());
			return key;
		} else {
			return this.generateRandomID(guildID);
		}
	}

	public String randomID() {
		Random random = new Random();
		String key = "";
		for (int i = 0; i <= 40; i++) {
			int id = random.nextInt(38);
			String part = getString(id);
			key = key + part;
		}
		return key;
	}

	public String getString(int id) {
		return chars.get(id);
	}

	public String getGuildIdFromKey(String key) {
		Sheet keys = getKeysSheet();
		Row row = keys.getRow(keys.getFirstRowNum());
		boolean hasKey = false;
		int colum = -1;
		for (Cell cell : row) {
			if (cell.getCellTypeEnum().equals(CellType.STRING)) {
				String cellValue = cell.getStringCellValue();
				if (cellValue.equals(key)) {
					hasKey = true;
					colum = cell.getAddress().getColumn();
				}
			}

		}
		if (hasKey) {
			Row guilds = keys.getRow(keys.getFirstRowNum() + 1);
			Cell cell = guilds.getCell(colum);
			String guildId = "";
			if (cell.getCellTypeEnum().equals(CellType.STRING)) {
				guildId = cell.getStringCellValue();
			}
			return guildId;
		} else {
			return "";
		}
	}

	public void removeKey(String key) {
		Sheet keys = getKeysSheet();
		Row row = keys.getRow(keys.getFirstRowNum());
		boolean hasKey = false;
		int colum = -1;
		for (Cell cell : row) {
			if (cell.getCellTypeEnum().equals(CellType.STRING)) {
				String cellValue = cell.getStringCellValue();
				if (cellValue.equals(key)) {
					hasKey = true;
					colum = cell.getAddress().getColumn();
					cell.setCellValue("");
				}
			}

		}
		if (hasKey) {
			Row guilds = keys.getRow(keys.getFirstRowNum() + 1);
			Cell cell = guilds.getCell(colum);
			if (cell.getCellTypeEnum().equals(CellType.STRING)) {
				cell.setCellValue("");
			}
		}
		this.saveDataWorkbook(keys.getWorkbook());
	}
}
