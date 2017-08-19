package tk.weeryan17.ava.objects.conditions.sub;

import java.io.InvalidObjectException;
import java.util.ArrayList;

import tk.weeryan17.ava.objects.enums.BrewedPotion;
import tk.weeryan17.ava.objects.enums.ItemType;

public class ItemCondition {
	private Object count;
	
	private int data;
	
	private Object durability;
	
	private ArrayList<EnchantmentCondition> enchantments;
	
	private ItemType item;
	
	private String nbt;
	
	private BrewedPotion potion;
	
	/**
	 * Sets the count value for the location.
	 * count needs to be a {@link Distance} object or an {@link Integer} object or null
	 * 
	 * @param count A {@link Distance} object or an {@link Integer} object or null
	 * @throws InvalidObjectException if count isn't a {@link Distance} object or an {@link Integer} object or null
	 */
	public void setCount(Object count) throws InvalidObjectException {
		if (count instanceof Distance || count instanceof Integer || count == null) {
			this.count = count;
		} else {
			InvalidObjectException e = new InvalidObjectException("Object count needs to be a Distance object Integer object or null");
			throw e;
		}
	}
	
	public Object getCount() {
		return count;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}
	
	/**
	 * Sets the durability value for the location.
	 * durability need to be a {@link Distance} object or an {@link Integer} object or null
	 * 
	 * @param durability A {@link Distance} object or an {@link Integer} object or null
	 * @throws InvalidObjectException if durability isn't a {@link Distance} object or an {@link Integer} object or null
	 */
	public void setdurability(Object durability) throws InvalidObjectException {
		if (durability instanceof Distance || durability instanceof Integer || durability == null) {
			this.durability = durability;
		} else {
			InvalidObjectException e = new InvalidObjectException("Object durability need to be a Distance object Integer object or null");
			throw e;
		}
	}
	
	public Object getdurability() {
		return durability;
	}

	public ArrayList<EnchantmentCondition> getEnchantments() {
		return enchantments;
	}

	public void setEnchantments(ArrayList<EnchantmentCondition> enchantments) {
		this.enchantments = enchantments;
	}

	public ItemType getItem() {
		return item;
	}

	public void setItem(ItemType item) {
		this.item = item;
	}

	public String getNbt() {
		return nbt;
	}

	public void setNbt(String nbt) {
		this.nbt = nbt;
	}

	public BrewedPotion getPotion() {
		return potion;
	}

	public void setPotion(BrewedPotion potion) {
		this.potion = potion;
	}
}
