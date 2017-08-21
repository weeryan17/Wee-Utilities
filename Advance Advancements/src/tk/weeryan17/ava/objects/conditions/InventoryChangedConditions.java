package tk.weeryan17.ava.objects.conditions;

import java.util.ArrayList;

import tk.weeryan17.ava.objects.Conditions;
import tk.weeryan17.ava.objects.conditions.sub.ItemCondition;
import tk.weeryan17.ava.objects.conditions.sub.SlotsCondition;

public class InventoryChangedConditions extends Conditions {
	private ArrayList<ItemCondition> items;
	
	private SlotsCondition slots;

	public ArrayList<ItemCondition> getItems() {
		return items;
	}

	public void setItems(ArrayList<ItemCondition> items) {
		this.items = items;
	}

	public SlotsCondition getSlots() {
		return slots;
	}

	public void setSlots(SlotsCondition slots) {
		this.slots = slots;
	}
}
