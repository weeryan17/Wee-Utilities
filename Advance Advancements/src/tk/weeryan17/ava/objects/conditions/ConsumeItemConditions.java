package tk.weeryan17.ava.objects.conditions;

import tk.weeryan17.ava.objects.Conditions;
import tk.weeryan17.ava.objects.conditions.sub.ItemCondition;

public class ConsumeItemConditions extends Conditions {
	private ItemCondition item;

	public ItemCondition getItem() {
		return item;
	}

	public void setItem(ItemCondition item) {
		this.item = item;
	}
}
