package com.weeryan17.dgs.commands.admin;

import com.weeryan17.dgs.commands.DiscordGroupsCommandBase;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;

public class GenerateCommand implements DiscordGroupsCommandBase {

	@Override
	public void onCommand(String[] args, IChannel channel, IUser sender) {
		if(args.length == 0){
			channel.sendMessage(sender.mention() + " This command must have arguments. Check at the help for more.");
		} else if(args.length == 1){
			switch(args[0]){
			case "key":{
				
			}
			}
		}
	}
	
}
