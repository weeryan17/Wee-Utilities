package com.weeryan17.sww.util.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.weeryan17.sww.WerewolfPlugin;
import com.weeryan17.sww.util.mannagers.WerewolfMannager;

public class Events implements Listener {
	WerewolfPlugin instance;
	public Events(WerewolfPlugin instance){
		this.instance = instance;
	}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e){
		Entity damagee = e.getEntity();
		Entity damager = e.getDamager();
		if(damagee instanceof Player && damager instanceof Player){
			WerewolfMannager werewolfMan = instance.getWerewolfMannager();
			if(!werewolfMan.isWerewolf((Player) damagee)){
				damagee.sendMessage(ChatColor.BLUE + "You feal str" + ChatColor.MAGIC + "a" + ChatColor.RESET + ChatColor.BLUE + "nge");
			}
		}
	}
}
