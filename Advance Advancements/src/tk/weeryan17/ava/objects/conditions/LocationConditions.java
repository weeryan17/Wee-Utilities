package tk.weeryan17.ava.objects.conditions;

import tk.weeryan17.ava.objects.Conditions;
import tk.weeryan17.ava.objects.conditions.sub.LocationCondition;

public class LocationConditions extends Conditions {
	private LocationCondition location;

	public LocationCondition getLocation() {
		return location;
	}

	public void setLocation(LocationCondition location) {
		this.location = location;
	}
}
