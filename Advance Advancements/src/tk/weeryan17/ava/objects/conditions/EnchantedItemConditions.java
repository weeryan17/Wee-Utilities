package tk.weeryan17.ava.objects.conditions;

import java.io.InvalidObjectException;

import tk.weeryan17.ava.objects.Conditions;
import tk.weeryan17.ava.objects.conditions.sub.Distance;
import tk.weeryan17.ava.objects.conditions.sub.ItemCondition;

public class EnchantedItemConditions extends Conditions {
	private ItemCondition item;
	
	private Object levels;

	public ItemCondition getItem() {
		return item;
	}

	public void setItem(ItemCondition item) {
		this.item = item;
	}
	
	/**
	 * Sets the levels value for the location.
	 * levels needs to be a {@link Distance} object or an {@link Integer} object or null
	 * 
	 * @param levels A {@link Distance} object or an {@link Integer} object or null
	 * @throws InvalidObjectException if levels isn't a {@link Distance} object or an {@link Integer} object or null
	 */
	public void setLevels(Object levels) throws InvalidObjectException {
		if (levels instanceof Distance || levels instanceof Integer || levels == null) {
			this.levels = levels;
		} else {
			InvalidObjectException e = new InvalidObjectException("Object levels need to be a Distance object Integer object or null");
			throw e;
		}
	}
	
	public Object getLevels() {
		return levels;
	}
}
