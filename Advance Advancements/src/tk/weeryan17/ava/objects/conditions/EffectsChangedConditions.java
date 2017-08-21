package tk.weeryan17.ava.objects.conditions;

import java.util.HashMap;

import tk.weeryan17.ava.objects.Conditions;
import tk.weeryan17.ava.objects.conditions.sub.EffectCondition;

public class EffectsChangedConditions extends Conditions {
	private HashMap<String, EffectCondition> effects;

	public HashMap<String, EffectCondition> getEffects() {
		return effects;
	}

	public void setEffects(HashMap<String, EffectCondition> effects) {
		this.effects = effects;
	}
}
