package com.weeryan17.dgs.commands.admin;

import com.weeryan17.dgs.DiscordGroups;
import com.weeryan17.dgs.commands.DiscordGroupsCommandBase;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

public class PermissionsCommand implements DiscordGroupsCommandBase {
	DiscordGroups instance;

	public PermissionsCommand(DiscordGroups instance) {
		this.instance = instance;
	}

	@Override
	public void onCommand(String[] args, IChannel channel, IUser sender) {
		channel.setTypingStatus(true);
		if (args.length <= 3 || args.length >= 5) {
			this.sendInvlid(args, channel, sender);
		} else {
			boolean add;
			switch(args[0].toLowerCase()){
			case "add" :{
				add = true;
			}
			break;
			case "remove" :{
				add = false;
			}
			break;
			default :{
				this.sendInvlid(args, channel, sender);
				return;
			}
			}
			boolean isuser;
			switch(args[1]){
			case "user" :{
				isuser = true;
			}
			break;
			case "group" :{
				isuser = false;
			}
			break;
			default :{
				this.sendInvlid(args, channel, sender);
				return;
			}
			}
			if(isuser){
				Object obj = instance.getMessageUtil().getUserFromString(args[2], channel);
				if(obj instanceof IUser){
					IUser user = (IUser) obj;
					if(add){
						
					} else {
						
					}
				} else {
					String error = (String) obj;
					
				}
			} else {
				Object obj = instance.getMessageUtil().getRoleFromString(args[2], channel);
				if(obj instanceof IRole){
					IRole role = (IRole) obj;
					if(add){
						
					} else {
						
					}
				} else {
					String error = (String) obj;
				}
			}
		}
	}

	public void sendInvlid(String[] args, IChannel channel, IUser sender) {
		EmbedBuilder builder = instance.getMessageUtil().getBaseEmbed(sender, channel);
		if (args.length == 0) {
			builder.appendField("Usage", "```^permissions <add/remove> <user/group> <Id> <permission>```", false);
			builder.appendField("Permissions",
					"```dg.server.generate\n" + "dg.server.manage\n" + "dg.server.stats\n" + "dg.server.web\n"
							+ "dg.perm.add\n" + "dg.perm.remove\n" + "dg.perm.group.add\n" + "dg.perm.group.remove\n"
							+ "dg.perm.group.modify\n```",
					false);
			builder.appendField("Other info",
					"Permissions with a `*` at the end do work so like `dg.server` will give them all the dg.server.<something> perms. You can find what the permissions do on the wiki.",
					false);
			channel.sendMessage(builder.build());
			channel.setTypingStatus(false);
		} else {
			boolean add = false;
			switch (args[0].toLowerCase()) {
			case "add": {
				add = true;
			}
				break;
			case "remove": {
				add = false;
			}
				break;
			default: {
				builder.appendField("Usage", "```^permissions <add/remove> <user/group> <Id> <permission>```", false);
				channel.sendMessage(builder.build());
				channel.setTypingStatus(false);
				return;
			}
			}
			if (args.length >= 2) {
				boolean user = false;
				switch (args[1].toLowerCase()) {
				case "user": {
					user = true;
				}
					break;
				case "group": {
					user = false;
				}
					break;
				default: {
					String addString = add ? "add" : "remove";
					builder.appendField("Usage", "```^permissions" + addString + "<user/group> <Id> <permission>```",
							false);
					channel.sendMessage(builder.build());
					channel.setTypingStatus(false);
					return;
				}
				}
				String addString = add ? "add" : "remove";
				String isUser = user ? "user" : "group";
				builder.appendField("Usage", "```^permissions" + addString + " " + isUser + " <Id> <permission>```",
						false);
				channel.sendMessage(builder.build());
				channel.setTypingStatus(false);
			} else {
				String addString = add ? "add" : "remove";
				builder.appendField("Usage", "```^permissions" + addString + "<user/group> <Id> <permission>```",
						false);
				channel.sendMessage(builder.build());
				channel.setTypingStatus(false);
			}
		}
	}
}
