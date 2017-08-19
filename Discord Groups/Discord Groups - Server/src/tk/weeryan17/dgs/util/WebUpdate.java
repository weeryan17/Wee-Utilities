package tk.weeryan17.dgs.util;

import java.util.TimerTask;
import java.util.logging.Level;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import sx.blah.discord.util.EmbedBuilder;
import tk.weeryan17.dgs.DiscordGroups;

public class WebUpdate extends TimerTask {
	
	DiscordGroups instance;
	
	public WebUpdate(DiscordGroups instance){
		this.instance = instance;
	}
	
	private static String ip;
	
	@Override
	public void run() {
		HttpResponse<JsonNode> resp = null;
		try {
			resp = Unirest.get("https://api.ipify.org?format=json").asJson();
		} catch (UnirestException e) {
			instance.getLogger().log("Error getting site stuff", Level.SEVERE, e, false);
			return;
		}
		if(ip == null){
			ip = (String) resp.getBody().getObject().get("ip");
		} else {
			String ip = (String) resp.getBody().getObject().get("ip");
			if(!WebUpdate.ip.equals(ip)){
				EmbedBuilder eb = new EmbedBuilder();
				eb.appendDesc("New ip found - time to update everything");
				eb.appendField("Old ip", "```" + WebUpdate.ip + "```", true);
				eb.appendField("New ip", "```" + ip + "```", true);
				WebUpdate.ip = ip;
				instance.getLogger().logEmbed(eb.build());
			}
		}
	}

}
