package com.weeryan17.dgs.permissions;

import java.util.HashMap;

import com.weeryan17.dgs.util.GuildUser;

import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;

public class DiscordGroupsPermissions {
	private static HashMap<GuildUser, DiscordGroupsPermissions> userPerms = new HashMap<GuildUser, DiscordGroupsPermissions>();
	private String[] perms;
	
	private IUser user;
	
	private IGuild guild;
	
	public DiscordGroupsPermissions(GuildUser user){
		this.user = user.getUser();
		this.guild = user.getGuild();
	}
	
	public static DiscordGroupsPermissions getUserPermissionsGroup(GuildUser user){
		return userPerms.get(user);
	}
	
	public boolean hasPerm(String perm){
		boolean hasPerm = false;
		for(IRole role: guild.getRolesForUser(user)){
			if(role.getPermissions().contains(Permissions.MANAGE_SERVER)){
				hasPerm = true;
			}
		}
		if(!hasPerm){
			for(String permsPerm: perms){
				if(permsPerm.equals(perm) || permsPerm.equals("*")){
					hasPerm = true;
				}
			}
		}
		return hasPerm;
	}
	
	public void setLocalPerms(String[] perms){
		this.perms = perms;
	}
	
	public static void updatePerms(GuildUser user){
		//TODO check perms storage for the guild and set the class local perms
	}
}
