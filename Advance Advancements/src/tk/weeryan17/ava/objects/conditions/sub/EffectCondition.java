package tk.weeryan17.ava.objects.conditions.sub;

import java.io.InvalidObjectException;

public class EffectCondition {
	private Object amplifier;
	
	private Object duration; 
	
	/**
	 * Sets the amplifier value for the location.
	 * amplifier needs to be a {@link Distance} object or an {@link Integer} object or null
	 * 
	 * @param amplifier A {@link Distance} object or an {@link Integer} object or null
	 * @throws InvalidObjectException if amplifier isn't a {@link Distance} object or an {@link Integer} object or null
	 */
	public void setAmplifier(Object amplifier) throws InvalidObjectException {
		if (amplifier instanceof Distance || amplifier instanceof Integer || amplifier == null) {
			this.amplifier = amplifier;
		} else {
			InvalidObjectException e = new InvalidObjectException("Object amplifier need to be a Distance object Integer object or null");
			throw e;
		}
	}
	
	public Object getAmplifier() {
		return amplifier;
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
	
	public Object getDuration() {
		return duration;
	}
}
