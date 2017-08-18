package com.weeryan17.sww;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import com.weeryan17.sww.events.Events;
import com.weeryan17.sww.util.mannagers.WerewolfMannager;
import com.weeryan17.sww.util.tasks.WorldChecker;
import com.weeryan17.utilities.api.Attribute;
import com.weeryan17.utilities.api.CommandApi;
import com.weeryan17.utilities.api.ConfigApi;
import com.weeryan17.utilities.api.MinecraftColor;
import com.weeryan17.utilities.api.PluginMannager;

import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;

public class WerewolfPlugin extends JavaPlugin {
	int id;
	ConfigApi api;
	int worldChecker;
	WerewolfMannager werewolfMannager;
	
	public ItemStack pureSilver;
	public ItemStack normalSilver;
	public ItemStack pureSilverSword;
	public ItemStack normalSilverSword;
	
	public ItemStack curePotion;
	public ItemStack UFcurePotion;
	
	@SuppressWarnings("deprecation")
	public void onEnable(){
		CommandApi cmdApi = new CommandApi();
		PluginMannager man = new PluginMannager();
		id = man.registerPlugin(this);
		api = new ConfigApi(id);
		//createItems();
		createRecipes();
		werewolfMannager = new WerewolfMannager(this);
		WerewolfCommand cmd = new WerewolfCommand(this);
		cmdApi.registerCommand(id, "/ww", "Main werewolf command", "");
		cmdApi.registerCommand(id, "/werewolf", "Main werewolf command", "");
		getCommand("ww").setExecutor(cmd);
		getCommand("werewolf").setExecutor(cmd);
		Events events = new Events(this);
		Bukkit.getPluginManager().registerEvents(events, this);
		//ChatChannel channel = new ChatChannel();
		WorldChecker worldChecker = new WorldChecker(this);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, worldChecker, 0L, 10L);
		//channel.createChannel("Werewolf Chat", "%RED%[%DARK_PURPLE&Werewolf%RED%]%WHITE%: ", "DARK_BLUE", "wc");
	}
	/*
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
		
		//Silver sword crafted with pure silver
		ArrayList<String> pureSilverSwordLore = new ArrayList<String>();
		pureSilverSwordLore.add(MinecraftColor.GREEN + "It's a sword made from pure silver.");
		pureSilverSwordLore.add("");
		pureSilverSwordLore.add(MinecraftColor.GREEN + "This sword is verry good at killing werewolves.");
		pureSilverSword = itemUtils.createItem(Material.IRON_SWORD, 0, 1, MinecraftColor.BLUE + "Pure Silver Sword", pureSilverSwordLore);
		pureSilverSword = itemUtils.addAttribute(pureSilverSword, Attribute.ATTACK_DAMAGE, 7, 0);
		
		//Silver sword crafted with normal Silver
		ArrayList<String> normalSilverSwordLore = new ArrayList<String>();
		normalSilverSwordLore.add(MinecraftColor.GREEN + "It's a sword made from silver.");
		normalSilverSwordLore.add("");
		normalSilverSwordLore.add(MinecraftColor.GREEN + "It's pretty good at killing werewolves");
		normalSilverSword = itemUtils.createItem(Material.IRON_SWORD, 0, 1, MinecraftColor.BLUE + "Silver Sword", normalSilverSwordLore);
		
		ArrayList<String> UFcurePotionLore = new ArrayList<String>();
		UFcurePotionLore.add(MinecraftColor.RED + "It's a cure potion but it's unfinished...");
		UFcurePotionLore.add(MinecraftColor.RED + "You can still drink it but no one knows what outcome it will have on you.");
		UFcurePotion = itemUtils.createItem(Material.POTION, 0, 1, MinecraftColor.RED + "Unfinished Cure Potion", UFcurePotionLore);
		
		ArrayList<String> curePotionLore = new ArrayList<String>();
		curePotionLore.add(MinecraftColor.RED + "It cures you from being a werewolf.");
		curePotionLore.add("");
		curePotionLore.add(MinecraftColor.RED + "Only death can cure one......");
		curePotion = itemUtils.createItem(Material.POTION, 0, 1, MinecraftColor.GREEN + "Cure Potion", curePotionLore);
	}
	*/
	
	@SuppressWarnings("deprecation")
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
		pureSilverSwordRecipe.shape("ASA", "BSB", "ARA");
		//pure silver
		pureSilverSwordRecipe.setIngredient('S', Material.IRON_INGOT);
		pureSilverSwordRecipe.setIngredient('R', Material.STICK);
		pureSilverSwordRecipe.setIngredient('B', Material.BLAZE_ROD);
		Bukkit.addRecipe(pureSilverSwordRecipe);
		
		ShapedRecipe normalSilverSwordRecipe = new ShapedRecipe(normalSilverSword);
		normalSilverSwordRecipe.shape("ASA", "BSB", "ARA");
		//normal silver
		normalSilverSwordRecipe.setIngredient('S', Material.IRON_INGOT);
		normalSilverSwordRecipe.setIngredient('R', Material.STICK);
		normalSilverSwordRecipe.setIngredient('B', Material.BLAZE_POWDER);
		Bukkit.addRecipe(normalSilverSwordRecipe);
		
		ShapelessRecipe UFcurePotionRecipe = new ShapelessRecipe(UFcurePotion);
		//Silver
		UFcurePotionRecipe.addIngredient(Material.IRON_INGOT);
		//Water bottle
		UFcurePotionRecipe.addIngredient(Material.POTION);
		UFcurePotionRecipe.addIngredient(Material.MILK_BUCKET);
		Bukkit.addRecipe(UFcurePotionRecipe);
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
        PacketPlayOutChat bar = new PacketPlayOutChat(icbc);
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(bar);
    }
	
	public String convertItemStackToJsonRegular(ItemStack itemStack) {
	    // First we convert the item stack into an NMS itemstack
	    net.minecraft.server.v1_12_R1.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
	    net.minecraft.server.v1_12_R1.NBTTagCompound compound = new NBTTagCompound();
	    compound = nmsItemStack.save(compound);

	    return compound.toString();
	}
	
	public void createDefaultClans(){
		
	}
	
}
