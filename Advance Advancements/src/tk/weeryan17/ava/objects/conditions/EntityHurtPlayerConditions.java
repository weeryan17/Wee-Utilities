package tk.weeryan17.ava.objects.conditions;

import tk.weeryan17.ava.objects.Conditions;
import tk.weeryan17.ava.objects.conditions.sub.DamageCondition;

public class EntityHurtPlayerConditions extends Conditions {
	private DamageCondition damage;

	public DamageCondition getDamage() {
		return damage;
	}

	public void setDamage(DamageCondition damage) {
		this.damage = damage;
	}
}
