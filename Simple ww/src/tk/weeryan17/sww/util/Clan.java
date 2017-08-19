package tk.weeryan17.sww.util;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Api for making clans.
 * more apis might come later.
 * 
 * @author weeryan17
 *
 */
public class Clan {
	private static HashMap<String, Clan> clans = new HashMap<String, Clan>();
	
	private ClanRunnable runnable;
	
	private ArrayList<Werewolf> werewolves;
	
	private String name;
	
	/**
	 * Creates a new Clan with the given name.
	 * 
	 * @param name The clans name.
	 */
	public Clan(String name){
		werewolves = new ArrayList<Werewolf>();
		this.name = name;
		clans.put(name, this);
	}
	
	/**
	 * Get's a Clan by it's name.
	 * 
	 * @param name The name of the clan.
	 * @return The Clan.
	 */
	public static Clan getClan(String name){
		return clans.get(name);
	}
	
	/**
	 * Adds a runnable for doing stuff.
	 * 
	 * @param plugin Your plugin. Needed for the scheduler.
	 * @param runable The Runnable class.
	 */
	public void addClanRunnable(JavaPlugin plugin, ClanRunnable runable){
		runable.werewolves(werewolves);
		this.runnable = runable;
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, runable, 0, 100L);
	}
	
	/**
	 * Adds a werewolf to the clan.
	 * Use WerewolfMannager.addWerewolfToClan(Werewolf, Clan); instead.
	 * 
	 * @param wolf The werewolf.
	 */
	public void addWerewolf(Werewolf wolf){
		werewolves.add(wolf);
		runnable.werewolves(werewolves);
		clans.put(name, this);
	}
	
	/**
	 * Removes a werewolf from the clan.
	 * Don't use this method the plugin will take care of this automatically.
	 * 
	 * @param wolf
	 */
	public void removeWerewolf(Werewolf wolf){
		werewolves.remove(wolf);
		runnable.werewolves(werewolves);
		clans.put(name, this);
	}
	
	/**
	 * Get's an ArrayList of the Werewolves in this clan.
	 * 
	 * @return ArrayList of the Werewolves in this clan.
	 */
	public ArrayList<Werewolf> getWerewolves(){
		return werewolves;
	}
	
	public String getName(){
		return name;
	}
}
