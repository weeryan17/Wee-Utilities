package com.weeryan17.dgs.util.web;

import static spark.Spark.*;

import org.json.JSONArray;

import com.google.gson.JsonParser;
import com.weeryan17.dgs.DiscordGroups;

public class WebReciver {
	
	DiscordGroups instance;
	public WebReciver(DiscordGroups instance){
		this.instance = instance;
	}
	
	public void initWeb(){
		port(0);
		post("/java", (req, res) -> {
			JsonParser parser = new JsonParser();
			Object obj = parser.parse(req.body());
			@SuppressWarnings("unused")
			JSONArray array = (JSONArray)obj;
			
			return "";
		});
	}
}
