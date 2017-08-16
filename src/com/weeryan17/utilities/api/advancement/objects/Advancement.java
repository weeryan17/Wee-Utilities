package com.weeryan17.utilities.api.advancement.objects;

import java.util.ArrayList;
import java.util.HashMap;

public class Advancement {
	private Display display;
	
	private String parent;
	
	private HashMap<String, Criteria> criteria;
	
	private ArrayList<String> requirements;
	
	private ArrayList<Reward> rewards;

	public Display getDisplay() {
		return display;
	}

	public void setDisplay(Display display) {
		this.display = display;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public HashMap<String, Criteria> getCriteria() {
		return criteria;
	}

	public void setCriteria(HashMap<String, Criteria> criteria) {
		this.criteria = criteria;
	}

	public ArrayList<String> getRequirements() {
		return requirements;
	}

	public void setRequirements(ArrayList<String> requirements) {
		this.requirements = requirements;
	}

	public ArrayList<Reward> getRewards() {
		return rewards;
	}

	public void setRewards(ArrayList<Reward> rewards) {
		this.rewards = rewards;
	}
}
