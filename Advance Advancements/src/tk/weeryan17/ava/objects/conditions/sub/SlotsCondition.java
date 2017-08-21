package tk.weeryan17.ava.objects.conditions.sub;

import java.io.InvalidObjectException;

public class SlotsCondition {
	private Object empty;
	
	private Object full;
	
	private Object ocupied;
	
	public Object getEmpty() {
		return empty;
	}
	
	/**
	 * Sets the empty value for the location.
	 * empty needs to be a {@link Distance} object or an {@link Integer} object or null
	 * 
	 * @param empty A {@link Distance} object or an {@link Integer} object or null
	 * @throws InvalidObjectException if empty isn't a {@link Distance} object or an {@link Integer} object or null
	 */
	public void setEmpty(Object empty) throws InvalidObjectException {
		if (empty instanceof Distance || empty instanceof Integer || empty == null) {
			this.empty = empty;
		} else {
			InvalidObjectException e = new InvalidObjectException("Object empty need to be a Distance object Integer object or null");
			throw e;
		}
	}
	
	public Object getFull() {
		return full;
	}
	
	/**
	 * Sets the full value for the location.
	 * full needs to be a {@link Distance} object or an {@link Integer} object or null
	 * 
	 * @param full A {@link Distance} object or an {@link Integer} object or null
	 * @throws InvalidObjectException if full isn't a {@link Distance} object or an {@link Integer} object or null
	 */
	public void setFull(Object full) throws InvalidObjectException {
		if (full instanceof Distance || full instanceof Integer || full == null) {
			this.full = full;
		} else {
			InvalidObjectException e = new InvalidObjectException("Object full need to be a Distance object Integer object or null");
			throw e;
		}
	}
	
	public Object getOcupied() {
		return ocupied;
	}
	
	/**
	 * Sets the ocupied value for the location.
	 * ocupied needs to be a {@link Distance} object or an {@link Integer} object or null
	 * 
	 * @param ocupied A {@link Distance} object or an {@link Integer} object or null
	 * @throws InvalidObjectException if ocupied isn't a {@link Distance} object or an {@link Integer} object or null
	 */
	public void setOcupied(Object ocupied) throws InvalidObjectException {
		if (ocupied instanceof Distance || ocupied instanceof Integer || ocupied == null) {
			this.ocupied = ocupied;
		} else {
			InvalidObjectException e = new InvalidObjectException("Object ocupied need to be a Distance object Integer object or null");
			throw e;
		}
	}
}
