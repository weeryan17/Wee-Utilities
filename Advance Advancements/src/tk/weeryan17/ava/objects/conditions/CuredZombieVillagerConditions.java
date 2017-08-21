package tk.weeryan17.ava.objects.conditions;

import tk.weeryan17.ava.objects.Conditions;
import tk.weeryan17.ava.objects.conditions.sub.EntityCondition;

public class CuredZombieVillagerConditions extends Conditions {
	private EntityCondition villager;
	
	private EntityCondition zombie;

	public EntityCondition getVillager() {
		return villager;
	}

	public void setVillager(EntityCondition villager) {
		this.villager = villager;
	}

	public EntityCondition getZombie() {
		return zombie;
	}

	public void setZombie(EntityCondition zombie) {
		this.zombie = zombie;
	}
}
