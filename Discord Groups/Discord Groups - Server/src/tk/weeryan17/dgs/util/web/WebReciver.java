package tk.weeryan17.dgs.util.web;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.qmetric.spark.authentication.AuthenticationDetails;
import com.qmetric.spark.authentication.BasicAuthenticationFilter;

import tk.weeryan17.dgs.DiscordGroups;
import tk.weeryan17.dgs.listeners.ClientListener;

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
			//this.storeToSheet(uuid, Long.valueOf(id));
			return "";
		});
		
		HashMap<String, Long> keepalives = new HashMap<>();
		
		ClientListener listen = new ClientListener(instance);
		
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				Iterator<Entry<String, Long>> it = keepalives.entrySet().iterator();
				while(it.hasNext()) {
					Entry<String, Long> pair = it.next();
					long left = pair.getValue() - 1;
					if(left <= 0) {
						keepalives.remove(pair.getKey());
						listen.removeKey(pair.getKey());
					}
				}
			}
		}, 0l, 1000l);
		
		path("/client", () -> {
			path("/:guild", () -> {
				post("/roles", (req, res) -> {
					return DiscordGroups.gson.toJson(listen.handleRoles(req.body(), req.params(":guild"), req.ip()));
				});
				post("/users", (req, res) -> {
					return DiscordGroups.gson.toJson(listen.handleUsers(req.body()));
				});
			});
			
			post("/keep_alive", (req, res) -> {
				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(req.body()).getAsJsonObject();
				String key = json.get("key").getAsString();
				String tolken = json.get("token").getAsString();
				JsonObject out = new JsonObject();
				out.addProperty("sucess", listen.keyMatches(tolken, key));
				if(!listen.keyMatches(tolken, key)) {
					instance.getLogger().log("Recived a token and a key that didn't match from ip: " + req.ip(), Level.WARNING, true);
				} else {
					keepalives.put(key, 45l);
				}
				return DiscordGroups.gson.toJson(out);
			});
			
			post("/verify", (req, res) -> {
				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(req.body()).getAsJsonObject();
				String key = json.get("key").getAsString();
				
				return "";
			});
		});
		
		before(new BasicAuthenticationFilter("/grafana/*", new AuthenticationDetails(instance.getProperties().getProperty("grafana-user"), instance.getProperties().getProperty("grafana-pass"))));
		
		post("/grafana", (req, res) -> {
			return "";
		});
		//TODO all of this
		path("/grafana", () -> {
			post("/search", (req, res) -> {
				return "{\n\n}";
			});
			post("/query", (req, res) -> {
				return "{\n\n}";
			});
			post("/annotations", (req, res) -> {
				return "{\n\n}";
			});
		});
	}
}
