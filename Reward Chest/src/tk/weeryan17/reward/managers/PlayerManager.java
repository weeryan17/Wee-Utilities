package tk.weeryan17.reward.managers;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import tk.weeryan17.reward.objects.RewardPlayer;

public class PlayerManager {
	Map<Player, RewardPlayer> players = new HashMap<>();
	public RewardPlayer getRewardPlayer(Player player){
		if(!players.containsKey(player)){
			return new RewardPlayer(player);
		} else {
			return players.get(player);
		}
	}
}
