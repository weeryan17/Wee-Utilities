package tk.weeryan17.ava.objects.conditions;

import tk.weeryan17.ava.objects.Conditions;
import tk.weeryan17.ava.objects.conditions.sub.DamageCondition;
import tk.weeryan17.ava.objects.conditions.sub.EntityCondition;

public class PlayerKillEntityConditions extends Conditions {
	private EntityCondition entity;
	
	private DamageCondition killing_blow;

	public EntityCondition getEntity() {
		return entity;
	}

	public void setEntity(EntityCondition entity) {
		this.entity = entity;
	}

	public DamageCondition getKilling_blow() {
		return killing_blow;
	}

	public void setKilling_blow(DamageCondition killing_blow) {
		this.killing_blow = killing_blow;
	}
}
