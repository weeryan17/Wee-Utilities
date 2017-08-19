package tk.weeryan17.ava.objects.conditions.sub;

import java.io.InvalidObjectException;

import tk.weeryan17.ava.objects.enums.EnchantmentType;

public class EnchantmentCondition {
	private EnchantmentType enchantment;
	
	private Object levels;

	public EnchantmentType getEnchantment() {
		return enchantment;
	}

	public void setEnchantment(EnchantmentType enchantment) {
		this.enchantment = enchantment;
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
