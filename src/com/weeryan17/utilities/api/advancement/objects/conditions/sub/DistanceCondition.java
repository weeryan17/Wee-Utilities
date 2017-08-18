package com.weeryan17.utilities.api.advancement.objects.conditions.sub;

/**
 * Used to represent distance in a condition.
 * 
 * @author weeryan17
 *
 */
public class DistanceCondition {
	private Distance absolute;
	
	private Distance horizontal;
	
	private Distance x;
	
	private Distance y;
	
	private Distance z;

	public Distance getAbsolute() {
		return absolute;
	}

	public void setAbsolute(Distance absolute) {
		this.absolute = absolute;
	}
	
	public Distance getHorizontal() {
		return horizontal;
	}

	public void setHorizontal(Distance horizontal) {
		this.horizontal = horizontal;
	}

	public Distance getX() {
		return x;
	}

	public void setX(Distance x) {
		this.x = x;
	}

	public Distance getY() {
		return y;
	}

	public void setY(Distance y) {
		this.y = y;
	}

	public Distance getZ() {
		return z;
	}

	public void setZ(Distance z) {
		this.z = z;
	}

	public class Distance {
		private int max;
		
		private int min;

		public int getMax() {
			return max;
		}

		public void setMax(int max) {
			this.max = max;
		}

		public int getMin() {
			return min;
		}

		public void setMin(int min) {
			this.min = min;
		}
	}
}
