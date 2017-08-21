package tk.weeryan17.ava.objects.conditions.sub;

import java.io.InvalidObjectException;

public class DamageCondition {
	private boolean blocked;
	
	private Object dealt;
	
	private EntityCondition direct_entity;
	
	private EntityCondition source_entity;
	
	private Object taken;
	
	private Type type;

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	
	/**
	 * Sets the dealt value for the location.
	 * dealt needs to be a {@link Distance} object or an {@link Integer} object or null
	 * 
	 * @param dealt A {@link Distance} object or an {@link Integer} object or null
	 * @throws InvalidObjectException if dealt isn't a {@link Distance} object or an {@link Integer} object or null
	 */
	public void setDealt(Object dealt) throws InvalidObjectException {
		if (dealt instanceof Distance || dealt instanceof Double || dealt == null) {
			this.dealt = dealt;
		} else {
			InvalidObjectException e = new InvalidObjectException("Object dealt need to be a Distance object Integer object or null");
			throw e;
		}
	}
	
	public Object getDealt() {
		return dealt;
	}

	public EntityCondition getDirect_entity() {
		return direct_entity;
	}

	public void setDirect_entity(EntityCondition direct_entity) {
		this.direct_entity = direct_entity;
	}

	public EntityCondition getSource_entity() {
		return source_entity;
	}

	public void setSource_entity(EntityCondition source_entity) {
		this.source_entity = source_entity;
	}
	
	/**
	 * Sets the taken value for the location.
	 * taken needs to be a {@link Distance} object or an {@link Integer} object or null
	 * 
	 * @param taken A {@link Distance} object or an {@link Integer} object or null
	 * @throws InvalidObjectException if taken isn't a {@link Distance} object or an {@link Integer} object or null
	 */
	public void setTaken(Object taken) throws InvalidObjectException {
		if (taken instanceof Distance || taken instanceof Integer || taken == null) {
			this.taken = taken;
		} else {
			InvalidObjectException e = new InvalidObjectException("Object taken need to be a Distance object Integer object or null");
			throw e;
		}
	}
	
	public Object getTaken() {
		return taken;
	}
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public class Type {
		private boolean bypasses_armor;
		
		private boolean bypasses_invulnerability;
		
		private boolean bypasses_magic;
		
		private EntityCondition direct_entity;
		
		private boolean is_explosion;
		
		private boolean is_fire;
		
		private boolean is_magic;
		
		private boolean is_projectile;
		
		private EntityCondition source_entity;

		public boolean isBypasses_armor() {
			return bypasses_armor;
		}

		public void setBypasses_armor(boolean bypasses_armor) {
			this.bypasses_armor = bypasses_armor;
		}

		public boolean isBypasses_invulnerability() {
			return bypasses_invulnerability;
		}

		public void setBypasses_invulnerability(boolean bypasses_invulnerability) {
			this.bypasses_invulnerability = bypasses_invulnerability;
		}

		public boolean isBypasses_magic() {
			return bypasses_magic;
		}

		public void setBypasses_magic(boolean bypasses_magic) {
			this.bypasses_magic = bypasses_magic;
		}

		public EntityCondition getDirect_entity() {
			return direct_entity;
		}

		public void setDirect_entity(EntityCondition direct_entity) {
			this.direct_entity = direct_entity;
		}

		public boolean isIs_explosion() {
			return is_explosion;
		}

		public void setIs_explosion(boolean is_explosion) {
			this.is_explosion = is_explosion;
		}

		public boolean isIs_fire() {
			return is_fire;
		}

		public void setIs_fire(boolean is_fire) {
			this.is_fire = is_fire;
		}

		public boolean isIs_magic() {
			return is_magic;
		}

		public void setIs_magic(boolean is_magic) {
			this.is_magic = is_magic;
		}

		public boolean isIs_projectile() {
			return is_projectile;
		}

		public void setIs_projectile(boolean is_projectile) {
			this.is_projectile = is_projectile;
		}

		public EntityCondition getSource_entity() {
			return source_entity;
		}

		public void setSource_entity(EntityCondition source_entity) {
			this.source_entity = source_entity;
		}
	}
}
