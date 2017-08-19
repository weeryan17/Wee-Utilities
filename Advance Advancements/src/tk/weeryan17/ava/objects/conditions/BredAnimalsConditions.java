package tk.weeryan17.ava.objects.conditions;

import tk.weeryan17.ava.objects.Conditions;
import tk.weeryan17.ava.objects.conditions.sub.EntityCondition;

/**
 * Represents the different conditions you can have when the bred animals trigger activates.
 * 
 * @author weeryan17
 *
 */
public class BredAnimalsConditions extends Conditions {
	private EntityCondition child;
	
	private EntityCondition parent;
	
	private EntityCondition partner;

	public EntityCondition getChild() {
		return child;
	}

	public void setChild(EntityCondition child) {
		this.child = child;
	}

	public EntityCondition getParent() {
		return parent;
	}

	public void setParent(EntityCondition parent) {
		this.parent = parent;
	}

	public EntityCondition getPartner() {
		return partner;
	}

	public void setPartner(EntityCondition partner) {
		this.partner = partner;
	}
}
