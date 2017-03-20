package com.weeryan17.dgs.commands.admin;

import org.apache.poi.ss.usermodel.Sheet;

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
			builder.appendField("Usage", "```^permissions <add/remove> <user/group> <Id> <permission>```", false);
			builder.appendField("Permissions",
					"```dg.server.generate\n" + "dg.server.manage\n" + "dg.server.stats\n" + "dg.server.web\n"
							+ "dg.perm.add\n" + "dg.perm.remove\n" + "dg.perm.group.add\n" + "dg.perm.group.remove\n"
							+ "dg.perm.group.modify\n```",
					false);
			builder.appendField("Other info",
					"Permissions with a star at the end do work so like `dg.server` will give them all the dg.server.<something> perms. You can find what the permissions do on the wiki.",
					false);
			channel.sendMessage(builder.build());
		} else if(args.length != 4){
			String add;
			String group;
			switch(args[0].toLowerCase()){
			case "add" :{
				add = "add";
			}
			break;
			case "remove" :{
				add = "remove";
			}
			break;
			default :{
				add = "<add/remove>";
			}
			
			switch(args[1].toLowerCase()){
			case "user" :{
				group = "user";
			}
			break;
			case "group" :{
				group = "group";
			}
			break;
			default :{
				group = "<user/group>";
			}
			}
			EmbedBuilder builder = instance.getMessageUtil().getBaseEmbed(sender, channel);
			builder.appendField("Usage", "```^permissions" + add + group + "<Id> <permission>```", false);
			channel.sendMessage(sender.mention() + " incorect usage", builder.build());
			
			}
		}  else {
			boolean add;
			boolean group;
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
				EmbedBuilder builder = instance.getMessageUtil().getBaseEmbed(sender, channel);
				builder.appendField("Usage", "```^permissions <add/remove> <userID/groupID> <permission>```", false);
				channel.sendMessage(sender.mention() + " incorect usage", builder.build());
				return;
			}
			}
			
			switch(args[1].toLowerCase()){
			case "user" :{
				group = false;
			}
			break;
			case "group" :{
				group = true;
			}
			break;
			default :{
				EmbedBuilder builder = instance.getMessageUtil().getBaseEmbed(sender, channel);
				builder.appendField("Usage", "```^permissions <add/remove> <userID/groupID> <permission>```", false);
				channel.sendMessage(sender.mention() + " incorect usage", builder.build());
				return;
			}
			}
			
			Sheet sheet = group ? instance.getStorage().getGuildRoleSheet(channel.getGuild().getID()) : instance.getStorage().getGuildUserSheet(channel.getGuild().getID());
			
			
		}
	}

}
