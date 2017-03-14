package com.weeryan17.dgc;

public class Updater implements Runnable{
	DiscordGroupsPlugin instance;
	public Updater(DiscordGroupsPlugin instance){
		this.instance = instance;
	}
	
	public void update(){
		
	}

	@Override
	public void run() {
		this.update();
	}
}
