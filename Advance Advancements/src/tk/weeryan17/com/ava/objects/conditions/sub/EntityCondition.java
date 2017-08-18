package tk.weeryan17.com.ava.objects.conditions.sub;

import java.util.HashMap;

/**
 * Used to represent an entity for conditions
 * 
 * @author weeryan17
 *
 */
public class EntityCondition {
	private DistanceCondition distance;
	
	private HashMap<String, EffectCondition> effects = new HashMap<String, EffectCondition>();

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
	
	//TODO more effect stuff
}
