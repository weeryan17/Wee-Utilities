package tk.weeryan17.ava.objects.conditions.sub;

import java.util.HashMap;

import org.bukkit.entity.Entity;

import tk.weeryan17.ava.objects.enums.EntityType;

/**
 * Used to represent an entity for conditions
 * 
 * @author weeryan17
 *
 */
public class EntityCondition {
	private DistanceCondition distance;
	
	private HashMap<String, EffectCondition> effects = new HashMap<String, EffectCondition>();
	
	private LocationCondition location;
	
	private String nbt;
	
	private EntityType type;

	public DistanceCondition getDistance() {
		return distance;
	}
	
	/**
	 * For entitys the distance is relative to the location.
	 * 
	 * @param distance The distance from the location
	 */
	public void setDistance(DistanceCondition distance) {
		this.distance = distance;
	}
	
	/**
	 * Removes an effect from the list of required effects.
	 * 
	 * @param effectName The name of the effect.
	 * @param condition The condition the effect needs to be.
	 */
	public void removeEffect(String effectName, EffectCondition condition) {
		effects.remove(effectName, condition);
	}

	public LocationCondition getLocation() {
		return location;
	}

	public void setLocation(LocationCondition location) {
		this.location = location;
	}

	public EntityType getType() {
		return type;
	}

	public void setType(EntityType type) {
		this.type = type;
	}

	public String getNbt() {
		return nbt;
	}

	public void setNbt(String nbt) {
		this.nbt = nbt;
	}
	
	public void setConditionFromEntity(Entity e) {
		//TODO this
	}
}
