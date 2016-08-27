package com.weeryan17.utilities;

import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.Recipe;

import com.weeryan17.utilities.api.CustomRecipe;

public class CraftEvent {
	public void onCraft(PrepareItemCraftEvent e){
		Recipe recipe = e.getRecipe();
		if(CustomRecipe.isPluginRecipe(recipe)){
			CustomRecipe custom = CustomRecipe.getRecipeByRecipe(recipe);
		}
	}
}
