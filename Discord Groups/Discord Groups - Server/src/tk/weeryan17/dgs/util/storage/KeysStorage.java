package tk.weeryan17.dgs.util.storage;

import java.io.File;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import tk.weeryan17.dgs.DiscordGroups;
import tk.weeryan17.dgs.util.GeneralUtils;

public class KeysStorage extends Storage {
	
	DiscordGroups instance;
	public KeysStorage(DiscordGroups instance) {
		super(instance);
		this.instance = instance;
	}
	
	private JsonObject getKeysJson() {
		File file = new File(dataFolder + "keys.json");
		if(!file.exists()) {
			createJsonFile(null, "keys.json");
		}
		return readJsonFile(null, "keys.json");
	}
	
	private void saveKeysJson(JsonObject json) {
		updateJsonFile(null, "keys.json", json);
	}
	
	public boolean keyMatches(String guildId, String key) {
		JsonObject json = getKeysJson();
		if(!json.has(guildId)) return false;
		ArrayList<String> keys = GeneralUtils.jsonArray(json.get(guildId).getAsJsonArray());
		return keys.contains(key);
	}
	
	public void addKey(String guildId, String key) {
		JsonObject json = getKeysJson();
		if(json == null) {
			json = new JsonObject();
		}
		JsonArray keys;
		if(!json.has(guildId)) {
			keys = new JsonArray();
		} else {
			keys = json.get(guildId).getAsJsonArray();
		}
		keys.add(key);
		json.add(guildId, keys);
		System.out.println("Lets see if it's this");
		saveKeysJson(json);
	}
	
	public ArrayList<String> getKeys(String guildId) {
		JsonObject json = getKeysJson();
		return GeneralUtils.jsonArray(json.get(guildId).getAsJsonArray());
	}

}
