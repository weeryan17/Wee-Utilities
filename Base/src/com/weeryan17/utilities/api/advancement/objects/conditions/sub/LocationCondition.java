package com.weeryan17.utilities.api.advancement.objects.conditions.sub;

import com.weeryan17.utilities.api.advancement.objects.enums.Biome;
import com.weeryan17.utilities.api.advancement.objects.enums.Dimension;
import com.weeryan17.utilities.api.advancement.objects.enums.Feature;

public class LocationCondition {
	private Biome biome;
	
	private Dimension dimension;
	
	private Feature feature;

	public Biome getBiome() {
		return biome;
	}

	public void setBiome(Biome biome) {
		this.biome = biome;
	}

	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	public Feature getFeature() {
		return feature;
	}

	public void setFeature(Feature feature) {
		this.feature = feature;
	}
}
