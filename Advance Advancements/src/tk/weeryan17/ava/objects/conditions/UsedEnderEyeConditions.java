package tk.weeryan17.ava.objects.conditions;

import java.io.InvalidObjectException;

import tk.weeryan17.ava.objects.Conditions;
import tk.weeryan17.ava.objects.conditions.sub.Distance;

public class UsedEnderEyeConditions extends Conditions {
	private Object distance;
	
	public Object getDistance() {
		return distance;
	}
	
	/**
	 * Sets the distance value for the location.
	 * distance needs to be a {@link Distance} object or an {@link Integer} object or null
	 * 
	 * @param distance A {@link Distance} object or an {@link Integer} object or null
	 * @throws InvalidObjectException if distance isn't a {@link Distance} object or an {@link Integer} object or null
	 */
	public void setDistance(Object distance) throws InvalidObjectException {
		if (distance instanceof Distance || distance instanceof Integer || distance == null) {
			this.distance = distance;
		} else {
			InvalidObjectException e = new InvalidObjectException("Object distance need to be a Distance object Integer object or null");
			throw e;
		}
	}
}
