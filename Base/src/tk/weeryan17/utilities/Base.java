package tk.weeryan17.utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import tk.weeryan17.ava.objects.conditions.*;
import tk.weeryan17.ava.objects.enums.Triggers;
import tk.weeryan17.utilities.BaseCommand;

import org.bukkit.plugin.java.JavaPlugin;
/**
 * The main class for this plugin
 * 
 * @author weeryan17
 *
 */
public class Base extends JavaPlugin {
	public static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public void onEnable() {
        BaseCommand cmd = new BaseCommand();
        this.getCommand("weeutilities").setExecutor(cmd);
        this.getCommand("wu").setExecutor(cmd);
    }
    
    public void constructConditions(){
    	Triggers.BRED_ANIMALS.setConditions(new BredAnimalsConditions());
    	Triggers.BREWED_POTION.setConditions(new BrewedPotionConditions());
    	Triggers.CHANGED_DIMENSION.setConditions(new ChangedDimensionConditions());
    	Triggers.CONSTRUCT_BEACON.setConditions(new ConstructBeaconConditions());
    	Triggers.CONSUME_ITEM.setConditions(new ConsumeItemConditions());
    	Triggers.CURED_ZOMBIE_VILLAGER.setConditions(new CuredZombieVillagerConditions());
    	Triggers.EFFECTS_CHANGED.setConditions(new EffectsChangedConditions());
    	Triggers.ENCHANTED_ITEM.setConditions(new EnchantedItemConditions());
    	Triggers.ENTER_BLOCK.setConditions(new EnterBlockConditions());
    	Triggers.ENTITY_HURT_PLAYER.setConditions(new EntityHurtPlayerConditions());
    	Triggers.ENTITY_KILLED_PLAYER.setConditions(new EntityKillPlayerConditions());
    	Triggers.INVENTORY_CHANGED.setConditions(new InventoryChangedConditions());
    	Triggers.ITEM_DURABILITY_CHANGED.setConditions(new ItemDuribilityChangedConditions());
    	Triggers.LEVITATION.setConditions(new LevitationConditions());
    	Triggers.LOCATION.setConditions(new LocationConditions());
    	Triggers.NETHER_TRAVEL.setConditions(new NetherTravelConditions());
    	Triggers.PLACED_BLOCK.setConditions(new PlacedBlockConditions());
    	Triggers.PLAYER_HURT_ENTITY.setConditions(new PlayerHurtEntityConditions());
    	Triggers.PLAYER_KILLED_ENTITY.setConditions(new PlayerKillEntityConditions());
    	Triggers.RECIPE_UNLOCKED.setConditions(new RecipeUnlockedConditions());
    	Triggers.SLEPT_IN_BED.setConditions(new SleptInBedConditions());
    	Triggers.SUMMONED_ENTITY.setConditions(new SummonedEntityConditions());
    	Triggers.TAME_ANIMAL.setConditions(new TameAnimalConditions());
    	Triggers.USED_ENDER_EYE.setConditions(new UsedEnderEyeConditions());
    	Triggers.USED_TOTEM.setConditions(new UsedTotemConditions());
    	Triggers.VILLAGER_TRADE.setConditions(new VillagerTradeConditions());
    }
}
