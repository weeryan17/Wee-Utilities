package com.weeryan17.utilities.api.advancement.objects.enums;

public enum Frames {
	CHALLENGE ("challenge"),
	GOAL ("goal"),
	TASK ("task");
	
	private final String s;
	
	Frames(String s){
		this.s = s;
	}
	
	public boolean equalsName(String s){
		return this.s.equals(s);
	}
	
	public String toString(){
		return s;
	}
}
