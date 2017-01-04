package com.weeryan17.sww.util.clans;

import java.util.ArrayList;

import com.weeryan17.sww.util.ClanRunnable;
import com.weeryan17.sww.util.Werewolf;

public class WitherFangRunnable implements ClanRunnable {
	ArrayList<Werewolf> werewolves;
	
	@Override
	public void run() {
		for(Werewolf wolf : werewolves){
			wolf.getPlayer();
		}
	}

	@Override
	public void werewolves(ArrayList<Werewolf> werewolves) {
		this.werewolves = werewolves;
	}

}
