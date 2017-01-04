package com.weeryan17.sww.events;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
		} else if(damagee instanceof Player && instance.getWerewolfMannager().isWerewolf((Player) damagee)){
			switch(damager.getType()){
			case PLAYER:{
				Player p = (Player) damager;
				ItemStack mainHand = p.getInventory().getItemInMainHand();
				ItemStack offHand = p.getInventory().getItemInOffHand();
				boolean mainHandAir = mainHand.getType().equals(Material.AIR) ? true : false;
				ItemStack attackItem = mainHandAir ? offHand : mainHand;
				if(instance.getWerewolfMannager().isSilverWepon(attackItem)){
					e.setDamage(e.getDamage() * 2);
				}
			}
			break;
			case ZOMBIE:{
				Zombie zombie = (Zombie) damager;
				ItemStack mainHand = zombie.getEquipment().getItemInMainHand();
				ItemStack offHand = zombie.getEquipment().getItemInOffHand();
				boolean mainHandAir = mainHand.getType().equals(Material.AIR) ? true : false;
				ItemStack attackItem = mainHandAir ? offHand : mainHand;
				if(instance.getWerewolfMannager().isSilverWepon(attackItem)){
					e.setDamage(e.getDamage() * 2);
				}
			}
			break;
			case SKELETON:{
				Skeleton skele = (Skeleton) damager;
				ItemStack mainHand = skele.getEquipment().getItemInMainHand();
				ItemStack offHand = skele.getEquipment().getItemInOffHand();
				boolean mainHandAir = mainHand.getType().equals(Material.AIR) ? true : false;
				ItemStack attackItem = mainHandAir ? offHand : mainHand;
				if(instance.getWerewolfMannager().isSilverWepon(attackItem)){
					e.setDamage(e.getDamage() * 2);
				}
			}
			break;
			default:{
				
			}
			}
		}
	}
	
	@EventHandler
	public void onCraft(CraftItemEvent e){
		ItemStack result = e.getRecipe().getResult();
		if(result.isSimilar(instance.normalSilverSword)){
			CraftingInventory inv = e.getInventory();
			if(!inv.getItem(2).isSimilar(instance.normalSilver) || !inv.getItem(5).isSimilar(instance.normalSilver)){
				e.setCancelled(true);
				e.getWhoClicked().sendMessage(ChatColor.RED + "You can only craft this with silver.");
			}
		} else if(result.isSimilar(instance.pureSilverSword)){
			CraftingInventory inv = e.getInventory();
			if(!inv.getItem(2).isSimilar(instance.pureSilver) || !inv.getItem(5).isSimilar(instance.pureSilver)){
				e.setCancelled(true);
				e.getWhoClicked().sendMessage(ChatColor.RED + "You can only craft this with pure silver.");
			}
		} else if(result.isSimilar(instance.pureSilver)){
			CraftingInventory inv = e.getInventory();
			for(int i = 1; i <= 9; i++){
				ItemStack item = inv.getItem(i);
				if(item != null){
					if(item.getType().equals(Material.IRON_INGOT)){
						if(!item.isSimilar(instance.normalSilver)){
							e.setCancelled(true);
							e.getWhoClicked().sendMessage(ChatColor.RED + "You can only craft pure silver with normal silver");
						}
					}
				}
			}
		} else if(result.isSimilar(instance.UFcurePotion)) {
			CraftingInventory inv = e.getInventory();
			for(int i = 1; i <= 9; i++){
				ItemStack item = inv.getItem(i);
				if(item != null){
					if(item.getType().equals(Material.IRON_INGOT)){
						if(!item.isSimilar(instance.normalSilver)){
							e.setCancelled(true);
							e.getWhoClicked().sendMessage(ChatColor.RED + "You can only craft a cure potion with normal silver");
						}
					}
				}
			}
		} else if(result.isSimilar(instance.normalSilver)) {
			
		} else {
			CraftingInventory inv = e.getInventory();
			for(ItemStack item: inv.getContents()){
				if(item.isSimilar(instance.curePotion) || item.isSimilar(instance.normalSilver) || item.isSimilar(instance.normalSilverSword)
						|| item.isSimilar(instance.pureSilver) || item.isSimilar(instance.pureSilverSword) || item.isSimilar(instance.UFcurePotion)){
					e.setCancelled(true);
					e.getWhoClicked().sendMessage(ChatColor.RED + "You may not use this item for normal crafting");
				}
			}
		}
	}
	
	@EventHandler
	public void onBrew(BrewEvent e){
		final BrewerInventory inv = e.getContents();
		for(int i = 0; i <= 2; i++){
			ItemStack item = inv.getItem(i);
			if(item != null){
				if(item.isSimilar(instance.UFcurePotion)){
					final boolean isFermented = inv.getIngredient().getType().equals(Material.FERMENTED_SPIDER_EYE) ? true : false;
					if(isFermented){
						inv.setItem(i, instance.curePotion);
					} else {
						inv.setItem(i, instance.UFcurePotion);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onDrink(PlayerItemConsumeEvent e){
		if(e.getItem().isSimilar(instance.curePotion)){
			if(instance.getWerewolfMannager().isWerewolf(e.getPlayer())){
				e.getPlayer().setHealth(0);
				instance.getWerewolfMannager().removeWerewolf(e.getPlayer());
			} else {
				e.setCancelled(true);
				e.getPlayer().sendMessage(ChatColor.RED + "You are not a werewolf so you arn't allowed to drink this");
			}
		} else if(e.getItem().isSimilar(instance.UFcurePotion)) {
			this.applyRandomBadEffect(e.getPlayer());
			this.applyRandomBadEffect(e.getPlayer());
		}
	}
	
	public void applyRandomBadEffect(Player p){
		Random random = new Random();
		int strenght = random.nextInt(5);
		strenght = strenght + 1;
		int duration = random.nextInt(99);
		duration = duration +1;
		int potionEffect = random.nextInt(10);
		PotionEffectType type = null;
		if(potionEffect == 0){
			type = PotionEffectType.BLINDNESS;
		} else if(potionEffect == 1){
			type = PotionEffectType.CONFUSION;
		} else if(potionEffect == 2){
			type = PotionEffectType.HARM;
		} else if(potionEffect == 3){
			type = PotionEffectType.HUNGER;
		} else if(potionEffect == 4){
			type = PotionEffectType.LEVITATION;
		} else if(potionEffect == 5){
			type = PotionEffectType.POISON;
		} else if(potionEffect == 6){
			type = PotionEffectType.SLOW;
		} else if(potionEffect == 7){
			type = PotionEffectType.SLOW_DIGGING;
		} else if(potionEffect == 8){
			type = PotionEffectType.UNLUCK;
		} else if(potionEffect == 9){
			type = PotionEffectType.WEAKNESS;
		} else if(potionEffect == 10){
			type = PotionEffectType.WITHER;
		}
		PotionEffect effect = new PotionEffect(type, duration, strenght);
		p.addPotionEffect(effect);
	}
}
