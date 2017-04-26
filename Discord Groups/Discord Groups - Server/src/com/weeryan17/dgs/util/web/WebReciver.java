package com.weeryan17.dgs.util.web;

import static spark.Spark.*;

import org.apache.poi.ss.usermodel.Sheet;

import com.weeryan17.dgs.DiscordGroups;

public class WebReciver {
	
	DiscordGroups instance;
	public WebReciver(DiscordGroups instance){
		this.instance = instance;
	}
	
	public void initWeb(){
		instance.getLogger().log("Init web!", false);
		int port =Integer.valueOf(instance.getProperties().getProperty("sparkPort"));
		port(port);
		post("/java", (req, res) -> {
			instance.getLogger().log("Got stuffs from web", false);
			String body = req.body();
			String uuid = "";
			String id = "";
			for(String string: body.split("&")){
				if(!string.equals("")){
					String[] value = string.split("=");
					switch(value[0]){
					case "mojang" :{
						uuid = value[1];
					}
					break;
					case "discord" :{
						id =  value[1];
					}
					}
				}
			}
			this.storeToSheet(uuid, id);
			return "Test";
		});
	}
	
	public void storeToSheet(String uuid, String id){
		Sheet sheet = instance.getStorage().getPlayerSheet();
		
	}
}
