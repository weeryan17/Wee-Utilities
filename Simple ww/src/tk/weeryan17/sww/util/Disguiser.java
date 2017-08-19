package tk.weeryan17.sww.util;

import org.bukkit.entity.Player;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.disguisetypes.watchers.WolfWatcher;

public class Disguiser {
	public void DisgusisePlayerAsWolf(Player p){
		MobDisguise dis = new MobDisguise(DisguiseType.WOLF);
		dis.setHearSelfDisguise(false);
		dis.setKeepDisguiseOnPlayerDeath(true);
		WolfWatcher wolf = (WolfWatcher) dis.getWatcher();
		wolf.setAngry(true);
		wolf.setCustomNameVisible(true);
		wolf.setCustomName("Werewolf");
		dis.setWatcher(wolf);
		DisguiseAPI.disguiseToAll(p, dis);
		DisguiseAPI.setViewDisguiseToggled(p, false);
	}
	
	public void UnDisguise(Player p){
		DisguiseAPI.undisguiseToAll(p);
	}
}
