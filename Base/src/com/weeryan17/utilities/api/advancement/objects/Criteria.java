package com.weeryan17.utilities.api.advancement.objects;

import com.weeryan17.utilities.api.advancement.objects.enums.Triggers;

public class Criteria {
	private Triggers trigger;
	
	private Conditions conditions;

	public Triggers getTrigger() {
		return trigger;
	}

	public void setTrigger(Triggers trigger) {
		this.trigger = trigger;
	}

	public Conditions getConditions() {
		return conditions;
	}

	public void setConditions(Conditions conditions) {
		this.conditions = conditions;
	}
}
