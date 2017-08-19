package tk.weeryan17.items;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import tk.weeryan17.utilities.api.ConfigApi;
import tk.weeryan17.utilities.api.ItemUtils;
import tk.weeryan17.utilities.api.MinecraftColor;
import tk.weeryan17.utilities.api.PluginMannager;

public class CustomItems extends JavaPlugin {
	static int id;
	ConfigApi confApi;
	ItemStack coreItem;
	public void onEnable(){
		id = new PluginMannager().registerPlugin(this);
		confApi = new ConfigApi(id);
		this.addCrafterRecipe();
		Events events = new Events(this);
		Bukkit.getServer().getPluginManager().registerEvents(events, this);
	}
	
	@SuppressWarnings("deprecation")
	public void addCrafterRecipe(){
		ItemUtils utils = new ItemUtils();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("The core of the crafter...");
		ItemStack item = utils.createItem(Material.OBSIDIAN, 0, 1, MinecraftColor.DARK_PURPLE + "Crafter Core", lore);
		coreItem = item;
		
		ShapedRecipe recipe = new ShapedRecipe(item);
		recipe.shape("OEO", "OAO", "ONO");
		recipe.setIngredient('O', Material.OBSIDIAN);
		recipe.setIngredient('E', Material.ENCHANTMENT_TABLE);
		recipe.setIngredient('A', Material.ANVIL);
		recipe.setIngredient('N', Material.NETHER_STAR);
		Bukkit.addRecipe(recipe);
	}
	
	//Core block manipulation
	public void saveCoreBlock(Block b){
		if(this.getCoreBlockConfig().contains("Cores")){
			@SuppressWarnings("unchecked")
			ArrayList<Location> blocks = (ArrayList<Location>) this.getCoreBlockConfig().get("Cores");
			blocks.add(b.getLocation());
			this.getCoreBlockConfig().set("Cores", blocks);
			this.saveCoreBlockConfig();
		} else {
			ArrayList<Location> blocks = new ArrayList<Location>();
			blocks.add(b.getLocation());
			this.getCoreBlockConfig().set("Cores", blocks);
			this.saveCoreBlockConfig();
		}
	}
	
	public void dropCoreBlock(Block b){
		this.deleteCoreBlock(b);
		b.setType(Material.AIR);
		World world = b.getWorld();
		world.dropItemNaturally(b.getLocation(), coreItem);
	}
	
	public void deleteCoreBlock(Block b){
		
	}
	
	public boolean isCoreBlock(Block b){
		return false;
	}
	
	public FileConfiguration getCoreBlockConfig(){
		return confApi.config("Cores", "blocks");
	}
	
	public void saveCoreBlockConfig(){
		confApi.saveConfigs("Cores", "blocks");
	}
}
