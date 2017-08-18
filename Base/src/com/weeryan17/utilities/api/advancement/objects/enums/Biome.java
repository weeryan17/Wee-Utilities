package com.weeryan17.utilities.api.advancement.objects.enums;

public enum Biome {
	BEACHES ("beaches"),
	BIRCH_FOREST ("birch_forest"),
	BIRCH_FOREST_HILLS ("birch_forest_hills"),
	COLD_BEACH ("cold_beach"),
	DEEP_OCEAN ("deep_ocean"),
	DESERT ("desert"),
	DESERT_HILLS ("desert_hills"),
	EXTREME_HILLS ("extreme_hills"),
	EXTREME_HILLS_WITH_TREES ("extreme_hills_with_trees"),
	FOREST ("forest"),
	FOREST_HILLS ("forest_hills"),
	FROZEN_OCEAN ("frozed_ocean"),
	FROZEN_RIVER ("frozen_river"),
	HELL ("hell"),
	ICE_FLATS ("ice_flats"),
	ICE_MOUNTAINS ("ice_mountains"),
	JUNGLE ("jungle"),
	JUNGLE_EDGE ("jungle_edge"),
	JUNGLE_HILLS ("jungle_hills"),
	MESA ("mesa"),
	MESA_CLEAR_ROCK ("mesa_clear_rock"),
	MESA_ROCK ("mesa_rock"),
	MUSHROOM_ISLAND ("mushroom_island"),
	MUSHROOM_ISLAND_SHORE ("mushroom_island_shore"),
	MUTATED_BIRCH_FOREST ("mutated_birch_forest"),
	MUTATED_BIRCH_FOREST_HILLS ("mutated_birch_forest_hills"),
	MUTATED_DESERT ("mutated_desert"),
	MUTATED_EXTREME_HILLS ("mutated_extreme_hills"),
	MUTATED_EXTREME_HILLS_WITH_TREES ("mutated_exetreme_hills_with_trees"),
	MUTATED_FOREST ("mutated_forest"),
	MUTATED_ICE_FLATS ("mutated_ice_flats"),
	MUTATED_JUNGLE ("mutated_jungle"),
	MUTATED_JUNGLE_EDGE ("mutated_jungle_edge"),
	MUTATED_MESA ("mutated_mesa"),
	MUTATED_MESA_CLEAR_ROCK ("mutated_mesa_clear_rock"),
	MUTATED_MESA_ROCK ("mutated_mesa_rock"),
	MUTATED_PLAINS ("mutated_plains"),
	MUTATED_REDWOOD_TAIGA ("mutated_redwood_taiga"),
	MUTATED_REDWOOD_TAIGA_HILLS ("mutated_redwood_taiga_hills"),
	MUTATED_ROOFED_FOREST ("mutated_roofed_forest"),
	MUTATED_SAVANNA ("mutated_savanna"),
	MUTATED_SAVANNA_ROCK ("mutated_savanna_rock"),
	MUTATED_SWAMPLAND ("mutated_swampland"),
	MUTATED_TAIGA ("mutated_taiga"),
	MUTATED_TAIGA_COLD ("mutated_taiga_cold"),
	OCEAN ("ocean"),
	PLAINS ("plains"),
	REDWOOD_TAIGA ("redwood_taiga"),
	REDWOOD_TAIGA_HILLS ("redwood_taiga_hills"),
	RIVER ("river"),
	ROOFED_FOREST ("roofed_forest"),
	SAVANNA ("savanna"),
	SAVANNA_ROCK ("savanna_rock"),
	SKY ("sky"),
	SMALLER_EXTREME_HILLS ("smaller_extreme_hills"),
	STONE_BEACH ("stone_beach"),
	SWAMPLAND ("swampland"),
	TAIGA ("taiga"),
	TAIGA_COLD ("taiga_cold"),
	TAIGA_COLD_HILLS ("taiga_cold_hills"),
	TAIGA_HILLS ("taiga_hills"),
	VOID ("void");
	
	private final String s;
	
	Biome(String s) {
		this.s = s;
	}
	
	public boolean equalsName(String s){
		return this.s.equals(s);
	}
	
	public String toString(){
		return s;
	}
}
