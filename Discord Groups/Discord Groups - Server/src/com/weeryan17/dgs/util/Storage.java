package com.weeryan17.dgs.util;

import java.io.IOException;
import java.util.logging.Level;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.weeryan17.dgs.DiscordGroups;

public class Storage {
	DiscordGroups instance;
	
	public Storage(DiscordGroups instance){
		this.instance = instance;
	}
	
	/**
	 * get's the sheet for storing player data.
	 * 
	 * @return The player sheet.
	 */
	public Sheet getPlayerSheet(){
		try {
			Workbook wb = new XSSFWorkbook(instance.getProperties().getProperty("workbookPath"));
			boolean sheetExists = false;
			Sheet sheet = null;
			for(int i = 0; i <= wb.getNumberOfSheets(); i++){
				sheet = wb.getSheetAt(i);
				String name = sheet.getSheetName();
				if(name.equals("Users")){
					sheetExists = true;
				}
			}
			if(sheetExists){
				wb.close();
				return sheet;
			} else {
				sheet = wb.createSheet("Users");
				wb.close();
				return sheet;
			}
		} catch (IOException e) {
			instance.getLogger().log("Chouldn't reed player data sheet", Level.SEVERE, e, false);
			System.exit(1);
			return null;
		}
	}
}