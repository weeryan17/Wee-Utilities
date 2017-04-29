package com.weeryan17.dgs.permissions;

public class PermissionsResponce {
	private boolean sucessfull;
	private String message;
	public PermissionsResponce(boolean sucessfull, String message){
		this.sucessfull = sucessfull;
		this.message = message;
	}
	
	public boolean getSucesfull(){
		return this.sucessfull;
	}
	
	public String getMessage(){
		return this.message;
	}
}
