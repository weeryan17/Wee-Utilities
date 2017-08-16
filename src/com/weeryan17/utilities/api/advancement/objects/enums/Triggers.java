package com.weeryan17.utilities.api.advancement.objects.enums;

import java.util.HashMap;

import com.weeryan17.utilities.api.advancement.objects.Conditions;

/**
 * Represents the different triggers that can trigger an advancement.
 * 
 * @author weeryan17
 *
 */
public enum Triggers {
	/**
	 * Triggers when a player breds some animals.
	 */
	BRED_ANIMALS ("minecraft:bred_animals"),
	
	/**
	 * Triggers when a player brews a potion.
	 */
	BREWED_POTION ("minecraft:brewed_potion"),
	
	/**
	 * Triggers when a player changes the dimension they are in.
	 */
	CHANGED_DIMENSION ("minecraft:changed_dimension"),
	
	/**
	 * Triggers when a player finished building a beacon.
	 */
	CONSTRUCT_BEACON ("minecraft:construct_beacon"),
	
	/**
	 * Triggers when a player either drinks a potion or eats food.
	 */
	CONSUME_ITEM ("minecraft:consume_item"),
	
	/**
	 * Triggers when a player cures a zombie villager
	 */
	CURED_ZOMBIE_VILLAGER ("minecraft:cured_zombie_villager"),
	
	/**
	 * Triggers when a player's effects change
	 */
	EFFECTS_CHANGED ("minecraft:effects_changed"),
	
	/**
	 * Triggers when a player enchants an item.
	 */
	ENCHANTED_ITEM ("minecraft:enchanted_item"),
	
	/**
	 * Triggers when a player enters a block (includes air).
	 * This checks every tick if the player entered a new block.
	 */
	ENTER_BLOCK ("minecraft:enter_block"),
	
	/**
	 * Triggers when a player is hurt by something.
	 */
	ENTITY_HURT_PLAYER ("minecraft:entity_hurt_player"),
	
	/**
	 * Triggers when a player dies to something.
	 */
	ENTITY_KILLED_PLAYER ("minecraft:entity_killed_player"),
	
	/**
	 * Only can be activated by command.
	 */
	IMPOSSIBLE ("minecraft:impossible"),
	
	/**
	 * Triggers when a player's inventory is changed.
	 */
	INVENTORY_CHANGED ("minecraft:inventory_changed"),
	
	/**
	 * Triggers when a item a player has changes it's durability (damage)
	 */
	ITEM_DURABILITY_CHANGED ("minecraft:item_durability_changed"),
	
	/**
	 * Triggers when a player gets applied with the levitation effect.
	 */
	LEVITATION ("minecraft:levitation"),
	
	/**
	 * Triggers when a player's location changes.
	 * This is checked every 20 ticks.
	 */
	LOCATION ("minecraft:location"),
	
	/**
	 * Triggers when a player enters the nether.
	 */
	NETHER_TRAVEL ("minecraft:nether_travel"),
	
	/**
	 * Triggers when a player places a block.
	 */
	PLACED_BLOCK ("minecraft:placed_block"),
	
	/**
	 * Triggers when a player hurts another entity (including other players).
	 */
	PLAYER_HURT_ENTITY ("minecraft:player_hurt_entity"),
	
	/**
	 * Triggers when a player kills another entity (including other players).
	 */
	PLAYER_KILLED_ENTITY ("minecraft:player_killed_entity"),
	
	/**
	 * Triggers when a player unlocks a new crafting recipe.
	 */
	RECIPE_UNLOCKED ("minecraft:recipe_unlocked"),
	
	/**
	 * Triggers when a player sleeps.
	 */
	SLEPT_IN_BED ("minecraft:slept_in_bed"),
	
	/**
	 * Triggers when a player summons an entity (doesn't active on command summon).
	 */
	SUMMONED_ENTITY ("minecraft:summoned_entity"),
	
	/**
	 * Triggers when a player tames an animal.
	 */
	TAME_ANIMAL ("minecraft:tame_animal"),
	
	/**
	 * Triggers when the world ticks next.
	 */
	TICK ("minecraft:tick"),
	
	/**
	 * Triggers when a player uses an eye of ender.
	 */
	USED_ENDER_EYE ("minecraft:used_ender_eye"),
	
	/**
	 * Triggers when a player uses a totem of undying.
	 */
	USED_TOTEM ("minecraft:used_totem"),
	
	/**
	 * Triggers when a player trades with a villager.
	 */
	VILLAGER_TRADE ("minecraft:villager_trade");
	
	private static HashMap<String, Conditions> conditions = new HashMap<String, Conditions>();
	
	private final String s;
	
	Triggers(String s){
		this.s = s;
	}
	
	public boolean equalsName(String s){
		return this.s.equals(s);
	}
	
	public String toString(){
		return s;
	}
	
	public Conditions getConditions(){
		return getConditionsMap().get(s);
	}
	
	/**
	 * Only use this if you want to add your own custom conditions.
	 * We suggest you also extends this enum into your own.
	 * 
	 * @return The current conditions added.
	 * @see Triggers#setConditions(HashMap)
	 */
	public static HashMap<String, Conditions> getConditionsMap() {
		return conditions;
	}
	
	/**
	 * Only use this if you want to add your own custom conditions.
	 * We suggest you also extends this enum into your own.
	 * 
	 * @param conditions The conditions you want to add including {@link Triggers#getConditions()}.
	 */
	public static void setConditions(HashMap<String, Conditions> conditions) {
		Triggers.conditions = conditions;
	}
}
