package com.weeryan17.dgs.commands.admin;

import java.util.ArrayList;

import com.weeryan17.dgs.DiscordGroups;
import com.weeryan17.dgs.commands.DiscordGroupsCommandBase;
import com.weeryan17.dgs.permissions.DiscordGroupsPermissions;
import com.weeryan17.dgs.util.GuildUser;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;

public class GenerateCommand implements DiscordGroupsCommandBase {
	ArrayList<String> ids;
	DiscordGroups instance;
	public GenerateCommand(DiscordGroups instance){
		this.instance = instance;
		ids = instance.getDevelopersIds();
	}

	@Override
	public void onCommand(String[] args, IChannel channel, IUser sender) {
		if(args.length == 0){
			channel.sendMessage(sender.mention() + " This command must have arguments. Check at the help for more.");
		} else if(args.length == 1){
			switch(args[0]){
			case "key":{
				DiscordGroupsPermissions perms = DiscordGroupsPermissions.getUserPermissions(new GuildUser(sender, channel.getGuild()));
				if(perms.hasPerm("dg.server.generate") || ids.contains(sender.getID())){
					
				} else {
					channel.sendMessage(sender.mention() + " you don't have permission to use this");
				}
			}
			}
		}
	}
	
}
