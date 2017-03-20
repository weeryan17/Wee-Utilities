package com.weeryan17.dgs.commands.admin;

import com.weeryan17.dgs.DiscordGroups;
import com.weeryan17.dgs.commands.DiscordGroupsCommandBase;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

public class PermissionsCommand implements DiscordGroupsCommandBase {
	DiscordGroups instance;

	public PermissionsCommand(DiscordGroups instance) {
		this.instance = instance;
	}

	@Override
	public void onCommand(String[] args, IChannel channel, IUser sender) {
		if (args.length == 0) {
			EmbedBuilder builder = instance.getMessageUtil().getBaseEmbed(sender, channel);
			builder.withTitle("^permissions");
			builder.appendField("Usage", "```^permissions <add/remove> <user/group> <permission>```", false);
			builder.appendField("Permissions",
					"```dg.server.generate\n" + "dg.server.manage\n" + "dg.server.stats\n" + "dg.server.web\n"
							+ "dg.perm.add\n" + "dg.perm.remove\n" + "dg.perm.group.add\n" + "dg.perm.group.remove\n"
							+ "dg.perm.group.modify\n```",
					false);
			builder.appendField("Other info",
					"Permissions with a star at the end do work so like `dg.server` will give them all the dg.server.<something> perms. You can find what the permissions do on the wiki.",
					false);
			channel.sendMessage(builder.build());
		} else if(args.length == 1 || args.length == 2 || args.length >= 4){
			switch(args[0]){
			case "add" :{
				EmbedBuilder builder = instance.getMessageUtil().getBaseEmbed(sender, channel);
				builder.appendField("Usage", "```^permissions add <user/group> <permission>```", false);
				channel.sendMessage(sender.mention() + " incorect usage", builder.build());
			}
			break;
			case "remove" :{
				EmbedBuilder builder = instance.getMessageUtil().getBaseEmbed(sender, channel);
				builder.appendField("Usage", "```^permissions remove <user/group> <permission>```", false);
				channel.sendMessage(sender.mention() + " incorect usage", builder.build());
			}
			break;
			default :{
				EmbedBuilder builder = instance.getMessageUtil().getBaseEmbed(sender, channel);
				builder.appendField("Usage", "```^permissions <add/remove> <userID/groupID> <permission>```", false);
				channel.sendMessage(sender.mention() + " incorect usage", builder.build());
			}
			}
		} else {
			switch(args[0]){
			case "add" :{
				//TODO write perm to storage
			}
			break;
			case "remove" :{
				//TODO remove perm from storage
			}
			break;
			default :{
				EmbedBuilder builder = instance.getMessageUtil().getBaseEmbed(sender, channel);
				builder.appendField("Usage", "```^permissions <add/remove> <userID/groupID> <permission>```", false);
				channel.sendMessage(sender.mention() + " incorect usage", builder.build());
			}
			}
		}
	}

}
