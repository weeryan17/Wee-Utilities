package tk.weeryan17.dgs.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import tk.weeryan17.dgs.DiscordGroups;

public class ClientListener {
	
	DiscordGroups instance;
	public ClientListener(DiscordGroups instance) {
		this.instance = instance;
	}
	
	/**
	 * Matches a token to key
	 */
	Map<String, String> keys = new HashMap<>();
	/**
	 * Matches a key to a token
	 */
	Map<String, String> tokens = new HashMap<>();
	/**
	 * Is a list of the current in use keys
	 */
	List<String> takenKeys = new ArrayList<>();
	/**
	 * Matches a key to a guild
	 */
	Map<String, String> guilds = new HashMap<>();
	
	public JsonObject handleRoles(String jsonString, String guild, String ip) {
		JsonParser parser = new JsonParser();
		JsonObject json = parser.parse(jsonString).getAsJsonObject();
		JsonObject verification = json.getAsJsonObject("varification");
		verification.addProperty("guild", guild);
		
		JsonObject out = new JsonObject();
		if(!verify(verification)) {
			out.addProperty("sucess", false);
			out.addProperty("reason", "verification");
			instance.getLogger().log("Verification failed from ip: " + ip, Level.WARNING, true);
		} else {
			
		}
		return out;
	}
	
	public JsonObject handleUsers(String json) {
		return new JsonObject();
	}
	
	public String verifyToken(String token) {
		return "";
	}
	
	public boolean keyMatches(String token, String key) {
		if(!keys.containsKey(token)) {
			return false;
		} else {
			return keys.get(token).equals(key);
		}
	}
	
	public boolean verify(JsonObject verification) {
		String token = verification.get("token").getAsString();
		String key = verification.get("key").getAsString();
		String guild = verification.get("guild").getAsString();
		
		if(!keys.containsKey(token)) {
			return false;
		}
		
		if(!keys.get(token).equals(key)) {
			return false;
		}
		
		if(!guilds.containsKey(key)) {
			return false;
		}
		
		if(!guilds.get(key).equals(guild)) {
			return false;
		}
		
		return true;
	}
	
	public void removeKey(String key) {
		String token = tokens.get(key);
		tokens.remove(key);
		keys.remove(token);
		takenKeys.remove(key);
		guilds.remove(key);
	}
	
	public JsonObject handleVerification(String guild, String key) {
		return null;
	}
}
