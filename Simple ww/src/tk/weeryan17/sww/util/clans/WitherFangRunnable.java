package tk.weeryan17.sww.util.clans;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import tk.weeryan17.sww.WerewolfPlugin;
import tk.weeryan17.sww.util.ClanRunnable;
import tk.weeryan17.sww.util.Werewolf;

public class WitherFangRunnable implements ClanRunnable {
	WerewolfPlugin instance;
	public WitherFangRunnable(WerewolfPlugin instance){
		this.instance = instance;
	}
	
	ArrayList<Werewolf> werewolves;
	
	@Override
	public void run() {
		for(Werewolf wolf : werewolves){
			Player p = wolf.getPlayer();
			if(!instance.getWerewolfMannager().isFullMoon(p.getWorld())){
				p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000, 0, false, false), true);
			}
		}
	}

	@Override
	public void werewolves(ArrayList<Werewolf> werewolves) {
		this.werewolves = werewolves;
	}

}
