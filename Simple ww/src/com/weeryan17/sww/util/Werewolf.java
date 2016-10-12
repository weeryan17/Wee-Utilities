package com.weeryan17.sww.util;

public class Werewolf {
	public boolean wolfState;
	public void toggleWolfState(){
		if(wolfState){
			this.setWolfState(false);
		} else {
			this.setWolfState(true);
		}
	}
	
	public void setWolfState(boolean wolfState){
		this.wolfState = wolfState;
		if(wolfState){
			
		} else {
			
		}
	}
	
	public boolean skyOpen(){
		return false;
	}
}
