package tk.weeryan17.utilities;

import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.Recipe;

import tk.weeryan17.utilities.api.CustomRecipe;

public class CraftEvent {
	public void onCraft(PrepareItemCraftEvent e){
		Recipe recipe = e.getRecipe();
		if(CustomRecipe.isPluginRecipe(recipe)){
			@SuppressWarnings("unused")
			CustomRecipe custom = CustomRecipe.getRecipeByRecipe(recipe);
			//TODO get this finished
		}
	}
}
