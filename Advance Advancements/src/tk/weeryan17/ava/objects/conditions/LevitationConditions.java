package tk.weeryan17.ava.objects.conditions;

import java.io.InvalidObjectException;

import tk.weeryan17.ava.objects.Conditions;
import tk.weeryan17.ava.objects.conditions.sub.Distance;
import tk.weeryan17.ava.objects.conditions.sub.DistanceCondition;

public class LevitationConditions extends Conditions {
	private DistanceCondition distance;
	
	private Object duration;

	public DistanceCondition getDistance() {
		return distance;
	}

	public void setDistance(DistanceCondition distance) {
		this.distance = distance;
	}
	
	public Object getDuration() {
		return duration;
	}
	
	/**
	 * Sets the duration value for the location.
	 * duration needs to be a {@link Distance} object or an {@link Integer} object or null
	 * 
	 * @param duration A {@link Distance} object or an {@link Integer} object or null
	 * @throws InvalidObjectException if duration isn't a {@link Distance} object or an {@link Integer} object or null
	 */
	public void setDuration(Object duration) throws InvalidObjectException {
		if (duration instanceof Distance || duration instanceof Integer || duration == null) {
			this.duration = duration;
		} else {
			InvalidObjectException e = new InvalidObjectException("Object duration need to be a Distance object Integer object or null");
			throw e;
		}
	}
}
