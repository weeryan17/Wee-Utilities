package com.weeryan17.dgs.commands.developer;

import java.util.ArrayList;

import com.weeryan17.dgs.DiscordGroups;
import com.weeryan17.dgs.commands.DiscordGroupsCommandBase;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;

public class StopCommand implements DiscordGroupsCommandBase {
	DiscordGroups instance;
	ArrayList<String> ids;
	public StopCommand(DiscordGroups instance){
		this.instance = instance;
		ids = instance.getDevelopersIds();
	}
	
	@Override
	public void onCommand(String[] args, IChannel channel, IUser sender) {
		if(ids.contains(sender.getID())){
			instance.getLogger().log("Stoping bot", true);
			System.exit(0);
		}
	}
	
	
}
