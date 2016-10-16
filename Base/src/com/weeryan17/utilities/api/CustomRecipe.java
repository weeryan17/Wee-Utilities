package com.weeryan17.utilities.api;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
/**
 * Allows you to create recipes where only specific items can be used.
 * 
 * @author weeryan17
 *
 */
public class CustomRecipe {
	ItemStack result;
	ShapedRecipe recipe;
	static HashMap<Recipe, CustomRecipe> recipeStorage = new HashMap<Recipe, CustomRecipe>();
	ArrayList<ItemStack> craftingList = new ArrayList<ItemStack>();
	/**
	 * Defines what will come out of the crafting process. This should ALLWAYS be called first.
	 * 
	 * @param result
	 */
	public void defineOutput(ItemStack result){
		recipe = new ShapedRecipe(result);
		this.result = result;
	}
	
	/**
	 * Defines the shape of the items. same as how you would use the bukkit system.
	 * 
	 * @param top The pattern for the top row.
	 * @param middle The pattern for the middle row.
	 * @param bottom The pattern for the bottom row.
	 */
	public void shape(String top, String middle, String bottom){
		recipe.shape(top, middle, bottom);
	}
	
	/**
	 * Set's items to be used in what spot during crafting.
	 * Same as the bukkit system
	 * 
	 * @param key The key defined when you set the pattern.
	 * @param item The item to be used.
	 */
	public void setIngredient(char key, ItemStack item){
		Material mat = item.getType();
		recipe.setIngredient(key, mat);
	}
	
	/**
	 * Acutely stores the recipe for use in events
	 */
	public void storeRecipe(){
		recipeStorage.put(recipe, this);
	}
	
	/**
	 * Checks if the given recipe is stored in the plugin.
	 * 
	 * @param recipe The recipe you want to check.
	 * @return a boolean representing if the recipe is contained in the plugin.
	 */
	public static boolean isPluginRecipe(Recipe recipe){
		if(getRecipeByRecipe(recipe) != null){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Get's the defined custom recipe based on a Bukkit recipe.
	 * 
	 * @param recipe The recipe you want to get the custom recipe from.
	 * @return The custom recipe. null if it isn't a part of this plugin.
	 */
	public static CustomRecipe getRecipeByRecipe(Recipe recipe){
		if(!recipeStorage.containsKey(recipe)){
			return null;
		} else {
			return recipeStorage.get(recipe);
		}
	}
}
