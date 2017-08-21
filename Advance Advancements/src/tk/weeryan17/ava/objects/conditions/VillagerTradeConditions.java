package tk.weeryan17.ava.objects.conditions;

import tk.weeryan17.ava.objects.Conditions;
import tk.weeryan17.ava.objects.conditions.sub.EntityCondition;
import tk.weeryan17.ava.objects.conditions.sub.ItemCondition;

public class VillagerTradeConditions extends Conditions {
	private ItemCondition item;
	
	private EntityCondition villager;

	public ItemCondition getItem() {
		return item;
	}

	public void setItem(ItemCondition item) {
		this.item = item;
	}

	public EntityCondition getVillager() {
		return villager;
	}

	public void setVillager(EntityCondition villager) {
		this.villager = villager;
	}
}
