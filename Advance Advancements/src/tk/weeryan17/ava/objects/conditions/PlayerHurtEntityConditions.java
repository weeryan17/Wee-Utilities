package tk.weeryan17.ava.objects.conditions;

import tk.weeryan17.ava.objects.Conditions;
import tk.weeryan17.ava.objects.conditions.sub.DamageCondition;
import tk.weeryan17.ava.objects.conditions.sub.EntityCondition;

public class PlayerHurtEntityConditions extends Conditions {
	private DamageCondition damage;
	
	private EntityCondition entity;

	public DamageCondition getDamage() {
		return damage;
	}

	public void setDamage(DamageCondition damage) {
		this.damage = damage;
	}

	public EntityCondition getEntity() {
		return entity;
	}

	public void setEntity(EntityCondition entity) {
		this.entity = entity;
	}
}
