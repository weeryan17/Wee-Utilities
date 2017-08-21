package tk.weeryan17.ava.objects.conditions;

import tk.weeryan17.ava.objects.Conditions;
import tk.weeryan17.ava.objects.conditions.sub.DistanceCondition;

public class NetherTravelConditions extends Conditions {
	private DistanceCondition distance;

	public DistanceCondition getDistance() {
		return distance;
	}

	public void setDistance(DistanceCondition distance) {
		this.distance = distance;
	}
}
