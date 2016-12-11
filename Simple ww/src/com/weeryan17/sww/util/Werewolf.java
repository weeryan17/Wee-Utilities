package com.weeryan17.sww.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
/**
 * Main class for werewolf handling.
 * 
 * @author weeryan17.
 *
 */
public class Werewolf {
	Player p;
	public Werewolf(Player p){
		this.p = p;
	}
	/**
	 * Toggles is the player is in wolf state.
	 */
	public boolean wolfState;
	public void toggleWolfState(){
		if(wolfState){
			this.setWolfState(false);
		} else {
			this.setWolfState(true);
		}
	}
	
	/**
	 * Set the wolf state of the player.
	 * 
	 * @param wolfState If the player is a wolf or isn't.
	 */
	public void setWolfState(boolean wolfState){
		this.wolfState = wolfState;
		Disguiser dis = new Disguiser();
		if(wolfState){
			dis.DisgusisePlayerAsWolf(p);
			p.setWalkSpeed(1.005f);
			p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100000, 3), true);
		} else {
			dis.UnDisguise(p);
			p.setWalkSpeed(1f);
			p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100000, 1), true);
		}
	}
	
	/**
	 * Checks if the sky is open for the werewolf to go into wolf state.
	 * 
	 * @return If the sky is open.
	 */
	public boolean skyOpen(){
		boolean skyOpen = true;
		int x = p.getLocation().getBlockX();
		int z = p.getLocation().getBlockZ();
		for(int y = p.getLocation().getBlockY(); y <= p.getWorld().getMaxHeight(); y++){
			Location loc = new Location(p.getWorld(), x, y, z);
			if(loc.getBlock().getType() != Material.AIR){
				skyOpen = false;
			}
		}
		return skyOpen;
	}
}
