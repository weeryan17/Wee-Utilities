package tk.weeryan17.ava.objects.conditions.sub;

import java.io.InvalidObjectException;

import tk.weeryan17.ava.objects.enums.Biome;
import tk.weeryan17.ava.objects.enums.Dimension;
import tk.weeryan17.ava.objects.enums.Feature;

public class LocationCondition {
	private Biome biome;
	
	private Dimension dimension;
	
	private Feature feature;
	
	private Object x;
	
	private Object y;
	
	private String nbt;

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

	public Object getX() {
		return x;
	}
	
	/**
	 * Sets the x value for the location.
	 * x need to be a {@link Distance} object or an {@link Integer} object or null
	 * 
	 * @param x A {@link Distance} object or an {@link Integer} object or null
	 * @throws InvalidObjectException if x isn't a {@link Distance} object or an {@link Integer} object or null
	 */
	public void setX(Object x) throws InvalidObjectException {
		if (x instanceof Distance || x instanceof Integer || x == null) {
			this.x = x;
		} else {
			InvalidObjectException e = new InvalidObjectException("Object x need to be a Distance object Integer object or null");
			throw e;
		}
	}
	
	public Object getY() {
		return y;
	}
	
	/**
	 * Sets the y value for the location.
	 * y need to be a {@link Distance} object or an {@link Integer} object or null
	 * 
	 * @param y A {@link Distance} object or an {@link Integer} object or null
	 * @throws InvalidObjectException if y isn't a {@link Distance} object or an {@link Integer} object or null
	 */
	public void setY(Object y) throws InvalidObjectException {
		if (y instanceof Distance || y instanceof Integer || y == null) {
			this.y = y;
		} else {
			InvalidObjectException e = new InvalidObjectException("Object y need to be a Distance object Integer object or null");
			throw e;
		}
	}

	public String getNbt() {
		return nbt;
	}

	public void setNbt(String nbt) {
		this.nbt = nbt;
	}
}
