package tk.weeryan17.dgs.util.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import tk.weeryan17.dgs.DiscordGroups;

public class Storage {
	DiscordGroups instance;

	ArrayList<String> chars;
	
	String dataFolder;

	public Storage(DiscordGroups instance) {
		this.instance = instance;
		dataFolder = instance.getJarLoc() + "/" + instance.getProperties().getProperty("dataFolder") + "/json/";
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
	
	protected JsonObject readJsonFile(String subFolder, String fileName) {
		
		File file = subFolder != null ? new File(dataFolder + subFolder + "/" + fileName) : new File(dataFolder + fileName);
		
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
		File file = subFolder != null ? new File(dataFolder + subFolder + "/" + fileName) : new File(dataFolder + fileName);
		file.mkdirs();
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(file));
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
		
	}
}
