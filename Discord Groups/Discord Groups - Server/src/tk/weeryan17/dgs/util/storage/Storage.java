package tk.weeryan17.dgs.util.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import tk.weeryan17.dgs.DiscordGroups;

public class Storage {
	DiscordGroups instance;
	
	String dataFolder;

	public Storage(DiscordGroups instance) {
		this.instance = instance;
		dataFolder = instance.getJarLoc() + "/" + instance.getProperties().getProperty("dataFolder") + "/json/";
	}
	
	protected JsonObject readJsonFile(String subFolder, String fileName) {
		
		File file = subFolder != null ? new File(dataFolder + subFolder + "/" + fileName) : new File(dataFolder + fileName);
		
		if(!file.exists()) {
			createJsonFile(subFolder, fileName);
		}
		
		FileReader reader;
		try {
			reader = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
		JsonParser parser = new JsonParser();
		JsonObject json = parser.parse(reader).getAsJsonObject();
		
		return json;
	}
	
	protected void createJsonFile(String subFolder, String fileName) {
		File file = subFolder != null ? new File(dataFolder + subFolder + "/" + fileName) : new File(dataFolder + fileName);;
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(DiscordGroups.gson.toJson(new JsonObject()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		try {
			writer.write(DiscordGroups.gson.toJson(new JsonObject()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}
	
	protected void updateJsonFile(String subFolder, String fileName, JsonObject json) {
		File file = subFolder != null ? new File(dataFolder + subFolder + "/" + fileName) : new File(dataFolder + fileName);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				return;
			}
			
		} else {
			file.delete();
			
			try {
				file.createNewFile();
			} catch (IOException e) {
				return;
			}
		}
		try {
			BufferedWriter write = new BufferedWriter(new FileWriter(file));
			write.write(DiscordGroups.gson.toJson(json));
			write.flush();
			write.close();
		} catch (IOException e) {
			instance.getLogger().log("Error Updating JSON file " + fileName, Level.SEVERE, e, true);
		}
	}
}
