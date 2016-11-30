package com.weeryan17.sww;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import com.weeryan17.sc.api.ChatChannel;
import com.weeryan17.sww.util.mannagers.WerewolfMannager;
import com.weeryan17.sww.util.tasks.WorldChecker;
import com.weeryan17.utilities.api.ConfigApi;
import com.weeryan17.utilities.api.ItemUtils;
import com.weeryan17.utilities.api.MinecraftColor;
import com.weeryan17.utilities.api.PluginMannager;

import net.minecraft.server.v1_11_R1.IChatBaseComponent;
import net.minecraft.server.v1_11_R1.PacketPlayOutChat;

public class WerewolfPlugin extends JavaPlugin {
	ItemUtils itemUtils;
	
	int id;
	ConfigApi api;
	int worldChecker;
	WerewolfMannager werewolfMannager;
	
	ItemStack pureSilver;
	ItemStack normalSilver;
	ItemStack pureSilverSword;
	
	public void onEnable(){
		PluginMannager man = new PluginMannager();
		id = man.registerPlugin(this);
		itemUtils = new ItemUtils();
		api = new ConfigApi(id);
		ChatChannel channel = new ChatChannel();
		WorldChecker worldChecker = new WorldChecker(this);
		this.worldChecker = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, worldChecker, 0L, 10L);
		channel.createChannel("Werewolf Chat", "%RED%[%DARK_PURPLE&Werewolf%RED%]", "DARK_BLUE", "sc");
		werewolfMannager = new WerewolfMannager(this);
	}
	
	public void createItems(){
		//Pure silver
		ArrayList<String> pureSilverLore = new ArrayList<String>();
		pureSilverLore.add(MinecraftColor.GREEN + "This silver is the purest silver you can get.");
		pureSilverLore.add(MinecraftColor.GREEN + "It's relly good at killing werewolves.");
		pureSilverLore.add("");
		pureSilverLore.add(MinecraftColor.GREEN + "It isn't verry usefull without it being crafted into a wepon.");
		pureSilver = itemUtils.createItem(Material.IRON_INGOT, 0, 1, MinecraftColor.BLUE + "Pure Silver", pureSilverLore);
		
		//Normal silver
		ArrayList<String> normalSilverLore = new ArrayList<String>();
		normalSilverLore.add(MinecraftColor.GREEN + "It's silver, but it doesn't look quite pure.");
		normalSilverLore.add(MinecraftColor.GREEN + "It's pretty good at killing werewolves.");
		normalSilverLore.add("");
		normalSilverLore.add(MinecraftColor.GREEN + "It isn't verry usefull without it being crafted into a wepon.");
		normalSilver = itemUtils.createItem(Material.IRON_INGOT, 0, 1, MinecraftColor.BLUE + "Silver", normalSilverLore);
		
		ArrayList<String> pureSilverSwordLore = new ArrayList<String>();
		pureSilverSwordLore.add(MinecraftColor.GREEN + "It's a sword made from pure silver.");
		pureSilverSwordLore.add("");
		pureSilverSwordLore.add(MinecraftColor.GREEN + "This sword is verry good at killing werewolves.");
		pureSilverSword = itemUtils.createItem(Material.IRON_SWORD, 0, 1, MinecraftColor.BLUE + "Pure Silver Sword", pureSilverSwordLore);
	}
	
	public void createRecipes(){
		ShapelessRecipe pureSilverRecipie = new ShapelessRecipe(pureSilver);
		//Going to be the normal silver for the iron.
		pureSilverRecipie.addIngredient(Material.IRON_INGOT);
		pureSilverRecipie.addIngredient(2, Material.QUARTZ_ORE);
		pureSilverRecipie.addIngredient(Material.BLAZE_ROD);
		Bukkit.addRecipe(pureSilverRecipie);
		
		ShapelessRecipe normalSilverRecipe = new ShapelessRecipe(normalSilver);
		normalSilverRecipe.addIngredient(Material.IRON_INGOT);
		normalSilverRecipe.addIngredient(2, Material.QUARTZ);
		normalSilverRecipe.addIngredient(Material.BLAZE_POWDER);
		Bukkit.addRecipe(normalSilverRecipe);
		
		ShapedRecipe pureSilverSwordRecipe = new ShapedRecipe(pureSilverSword);
		pureSilverSwordRecipe.shape("ASA", "ASA", "ARS");
		//Pure silver.
		pureSilverSwordRecipe.setIngredient('S', Material.IRON_INGOT);
		pureSilverSwordRecipe.setIngredient('R', Material.STICK);
		Bukkit.addRecipe(pureSilverSwordRecipe);
		
	}
	
	public FileConfiguration getPlayerDataConfig(UUID player){
		return api.config(player.toString(), "Data/Players");
	}
	
	public void savePlayerDataConfig(UUID player){
		api.saveConfigs(player.toString(), "Data/Players");
	}
	
	public FileConfiguration getWerewolfListConfig(){
		return api.config("werewolves", "Data");
	}
	
	public void saveWerewolfListConfig(){
		api.saveConfigs("werewolves", "Data");
	}
	
	public WerewolfMannager getWerewolfMannager(){
		return werewolfMannager;
	}
	
	/*
	 * Thank you members on spigot for this code for action bars.
	 * This isn't my code.
	 */
	public void sendActionbar(Player p, String message) {
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', message) + "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte)2);
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(bar);
    }
	
}
