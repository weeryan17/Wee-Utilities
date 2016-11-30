package com.weeryan17.sww.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

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
		if(damagee instanceof Player && damager instanceof Wolf){
			WerewolfMannager werewolfMan = instance.getWerewolfMannager();
			if(!werewolfMan.isWerewolf((Player) damagee)){
				damagee.sendMessage(ChatColor.BLUE + "You feal str" + ChatColor.MAGIC + "a" + ChatColor.RESET + ChatColor.BLUE + "nge");
				instance.getWerewolfMannager().addWerewolf((Player) damagee);
			}
		} else if(damagee instanceof Player){
			switch(damager.getType()){
			case PLAYER:{
				Player p = (Player) damager;
				ItemStack mainHand = p.getInventory().getItemInMainHand();
				ItemStack offHand = p.getInventory().getItemInOffHand();
				boolean mainHandAir = mainHand.getType().equals(Material.AIR) ? true : false;
				ItemStack attackItem = mainHandAir ? offHand : mainHand;
			}
			break;
			case ZOMBIE:{
				
			}
			break;
			case SKELETON:{
				
			}
			break;
			default:{
				
			}
			}
		}
	}
}
