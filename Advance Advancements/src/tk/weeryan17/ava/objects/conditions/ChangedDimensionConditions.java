package tk.weeryan17.ava.objects.conditions;

import tk.weeryan17.ava.objects.Conditions;
import tk.weeryan17.ava.objects.enums.Dimension;

public class ChangedDimensionConditions extends Conditions {
	private Dimension from;
	
	private Dimension to;

	public Dimension getFrom() {
		return from;
	}

	public void setFrom(Dimension from) {
		this.from = from;
	}

	public Dimension getTo() {
		return to;
	}

	public void setTo(Dimension to) {
		this.to = to;
	}
}
