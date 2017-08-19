package tk.weeryan17.sc;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import tk.weeryan17.sc.api.ChatChannel;

public class Events implements Listener {
	Chat instance;
	public Events(Chat instance){
		this.instance = instance;
	}
	
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		UUID uuid = p.getUniqueId();
		if(instance.getPlayerDataConfig().contains(uuid.toString() + ".channel")){
			if(!instance.getPlayerDataConfig().get(uuid.toString() + ".channel").equals("")){
				e.setCancelled(true);
				String name = instance.getPlayerDataConfig().get(uuid.toString() + ".channel").toString();
				ChatChannel channel = ChatChannel.getChannelByName(name);
				channel.broadcastPlayerMessage(p, e.getMessage());
			}
		}
	}
}
