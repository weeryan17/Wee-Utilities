package tk.weeryan17.ava.objects.conditions;

import java.io.InvalidObjectException;

import tk.weeryan17.ava.objects.Conditions;
import tk.weeryan17.ava.objects.conditions.sub.Distance;

public class ConstructBeaconConditions extends Conditions {
	private Object level;
	
	public void setLevel(Object level) throws InvalidObjectException {
		if (level instanceof Distance || level instanceof Integer || level == null) {
			this.level = level;
		} else {
			InvalidObjectException e = new InvalidObjectException("Object level need to be a Distance object Integer object or null");
			throw e;
		}
	}
	
	public Object getLevel() {
		return level;
	}
}
