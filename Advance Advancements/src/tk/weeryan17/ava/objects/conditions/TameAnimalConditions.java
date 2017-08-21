package tk.weeryan17.ava.objects.conditions;

import tk.weeryan17.ava.objects.Conditions;
import tk.weeryan17.ava.objects.conditions.sub.EntityCondition;

public class TameAnimalConditions extends Conditions {
	private EntityCondition entity;

	public EntityCondition getEntity() {
		return entity;
	}

	public void setEntity(EntityCondition entity) {
		this.entity = entity;
	}
}
