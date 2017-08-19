package tk.weeryan17.dgs.util.web;

import static spark.Spark.*;

import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import tk.weeryan17.dgs.DiscordGroups;

public class WebReciver {

	DiscordGroups instance;

	public WebReciver(DiscordGroups instance) {
		this.instance = instance;
	}

	public void initWeb() {
		instance.getLogger().log("Init web!", false);
		int port = Integer.valueOf(instance.getProperties().getProperty("sparkPort"));
		port(port);
		post("/java", (req, res) -> {
			instance.getLogger().log("Got stuffs from web", false);
			String body = req.body();
			String uuid = "";
			String id = "";
			for (String string : body.split("&")) {
				if (!string.equals("")) {
					String[] value = string.split("=");
					switch (value[0]) {
					case "mojang": {
						uuid = value[1];
					}
						break;
					case "discord": {
						id = value[1];
					}
					}
				}
			}
			this.storeToSheet(uuid, Long.valueOf(id));
			return "Test";
		});
	}

	public void storeToSheet(String uuid, Long id) {
		Sheet keysSheet = instance.getStorage().getPlayerSheet();
		Row keysRow = keysSheet.getRow(keysSheet.getFirstRowNum());
		ArrayList<String> keys = new ArrayList<String>();
		for (Cell cell : keysRow) {
			if (cell.getCellTypeEnum().equals(CellType.STRING)) {
				keys.add(cell.getStringCellValue());
			}
		}
		String key = uuid;
		if (!keys.contains(key)) {
			Cell cell = keysRow.createCell(keysRow.getPhysicalNumberOfCells() + 1);
			cell.setCellValue(key);
			Row idRow = keysSheet.getRow(keysSheet.getFirstRowNum() + 1);
			idRow.createCell(cell.getColumnIndex()).setCellValue(String.valueOf(id));
			instance.getStorage().saveDataWorkbook(keysSheet.getWorkbook());
		} else {

		}
	}
}
