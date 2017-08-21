package tk.weeryan17.ava.objects.conditions;

import java.io.InvalidObjectException;

import tk.weeryan17.ava.objects.Conditions;
import tk.weeryan17.ava.objects.conditions.sub.Distance;
import tk.weeryan17.ava.objects.conditions.sub.ItemCondition;

public class ItemDuribilityChangedConditions extends Conditions {
	private Object delta;
	
	private Object durability;
	
	private ItemCondition item;
	
	public Object getDelta() {
		return delta;
	}
	
	/**
	 * Sets the delta value for the location.
	 * delta needs to be a {@link Distance} object or an {@link Integer} object or null
	 * 
	 * @param delta A {@link Distance} object or an {@link Integer} object or null
	 * @throws InvalidObjectException if delta isn't a {@link Distance} object or an {@link Integer} object or null
	 */
	public void setDelta(Object delta) throws InvalidObjectException {
		if (delta instanceof Distance || delta instanceof Integer || delta == null) {
			this.delta = delta;
		} else {
			InvalidObjectException e = new InvalidObjectException("Object delta need to be a Distance object Integer object or null");
			throw e;
		}
	}
	
	public Object getDurability() {
		return durability;
	}
	
	/**
	 * Sets the durability value for the location.
	 * durability needs to be a {@link Distance} object or an {@link Integer} object or null
	 * 
	 * @param durability A {@link Distance} object or an {@link Integer} object or null
	 * @throws InvalidObjectException if durability isn't a {@link Distance} object or an {@link Integer} object or null
	 */
	public void setDurability(Object durability) throws InvalidObjectException {
		if (durability instanceof Distance || durability instanceof Integer || durability == null) {
			this.durability = durability;
		} else {
			InvalidObjectException e = new InvalidObjectException("Object durability need to be a Distance object Integer object or null");
			throw e;
		}
	}

	public ItemCondition getItem() {
		return item;
	}

	public void setItem(ItemCondition item) {
		this.item = item;
	}
}
