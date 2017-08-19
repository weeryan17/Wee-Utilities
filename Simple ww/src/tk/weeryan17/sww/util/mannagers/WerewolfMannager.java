package tk.weeryan17.sww.util.mannagers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import tk.weeryan17.sww.WerewolfPlugin;
import tk.weeryan17.sww.util.Clan;
import tk.weeryan17.sww.util.Werewolf;

public class WerewolfMannager {
	WerewolfPlugin instance;
	HashMap<Player, Werewolf> werewolves = new HashMap<Player, Werewolf>();
	ArrayList<UUID> uuidList = new ArrayList<UUID>();
	
	ArrayList<String> itemNames = new ArrayList<String>();
	
	public WerewolfMannager(WerewolfPlugin instance){
		if(instance.getWerewolfListConfig().contains("Werewolves")){
			@SuppressWarnings("unchecked")
			ArrayList<UUID> werewolves = (ArrayList<UUID>) instance.getWerewolfListConfig().get("Werewolves");
			for(UUID uuid : werewolves){
				Player p = Bukkit.getPlayer(uuid);
				Werewolf werewolf = new Werewolf(p);
				this.werewolves.put(p, werewolf);
				uuidList.add(uuid);
			}
		}
		itemNames.add("puresilversword");
		itemNames.add("normalsilversword");
		itemNames.add("puresilver");
		itemNames.add("normalsilver");
		itemNames.add("curepotion");
		itemNames.add("unfinishedcurepotion");
		this.instance = instance;
	}
	
	public Werewolf getWerewolfByPlayer(Player p){
		return werewolves.get(p);
	}
	
	public ArrayList<Werewolf> getWerewolvesInWorld(World world){
		ArrayList<Werewolf> werewolves = new ArrayList<Werewolf>();
		ArrayList<Player> players = (ArrayList<Player>) world.getPlayers();
		for(Player p: players){
			if(this.isWerewolf(p)){
				werewolves.add(this.getWerewolfByPlayer(p));
			}
		}
		return werewolves;
	}
	
	public boolean isWerewolf(Player p){
		return werewolves.containsKey(p);
	}
	
	public void addWerewolf(Player p){
		p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100000, 0), true);
		UUID uuid = p.getUniqueId();
		Werewolf wolf = new Werewolf(p);
		this.werewolves.put(p, wolf);
		uuidList.add(uuid);
		instance.getWerewolfListConfig().set("Werewolves", uuidList);
		instance.saveWerewolfListConfig();
	}
	
	public boolean toggleWerewolf(Player p){
		if(this.isWerewolf(p)){
			this.removeWerewolf(p);
			return false;
		} else {
			this.addWerewolf(p);
			return true;
		}
	}
	
	public void removeWerewolf(Player p){
		p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
		werewolves.remove(p);
		uuidList.remove(p.getUniqueId());
		instance.getWerewolfListConfig().set("Werewolves", uuidList);
		instance.saveWerewolfListConfig();
	}
	
	public boolean isSilverWepon(ItemStack item){
		if(item.isSimilar(instance.pureSilverSword) || item.isSimilar(instance.normalSilverSword)){
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isWerewolfItem(String itemName){
		itemName = itemName.toLowerCase();
		return itemNames.contains(itemName);
	}
	
	public ItemStack getWerewolfItem(String itemName){
		if(this.isWerewolfItem(itemName)){
			switch(itemName.toLowerCase()){
			case "puresilversword" :{
				return instance.pureSilverSword;
			}
			
			case "normalsilversword" :{
				return instance.normalSilverSword;
			}
			
			case "puresilver" :{
				return instance.pureSilver;
			}
			
			case "normalsilver" :{
				return instance.normalSilver;
			}
			
			case "curepotion" :{
				return instance.curePotion;
			}
			
			case "unfinishedcurepotion" :{
				return instance.UFcurePotion;
			}
			
			default :{
				return null;
			}
			
			}
		} else {
			return null;
		}
	}
	
	public boolean playersInWorld(World world){
		return world.getPlayers().size() >= 1 ? true : false;
	}
	
	public int getDaysTillMoon(World world){
		long day = world.getFullTime() / 24000;
		int phase = (int) (day % 8);
		if(phase == 0){
			return 0;
		} else {
			return 9 - phase;
		}
	}
	
	public boolean isFullMoon(World world){
		if(this.getDaysTillMoon(world) == 0 && world.getTime() >= 13000){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Adds a Werewolf to the given Clan.
	 * 
	 * @param wolf The Werewolf to add to the clan.
	 * @param clan The Clan to ass the Werewolf to.
	 */
	public void addWerewolfToClan(Werewolf wolf, Clan clan){
		clan.addWerewolf(wolf);
		wolf.putInClan(clan);
	}
}
