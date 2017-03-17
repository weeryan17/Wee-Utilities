package com.weeryan17.dgs.permissions;

import java.util.ArrayList;
import java.util.HashMap;

import com.weeryan17.dgs.DiscordGroups;
import com.weeryan17.dgs.util.GuildUser;

import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;

public class DiscordGroupsPermissions {
	private static HashMap<GuildUser, DiscordGroupsPermissions> userPerms = new HashMap<GuildUser, DiscordGroupsPermissions>();
	private static String[] allPerms = {
			"dg",
			"dg.server",
			"dg.server.generate",
			"dg.server.manage",
			"dg.server.stats",
			"dg.server.web",
			"dg.perm",
			"dg.perm.add",
			"dg.perm.remove",
			"dg.perm.group",
			"dg.perm.group.add",
			"dg.perm.group.remove",
			"dg.perm.group.modify"
			};
	
	private String[] perms;
	
	private IUser user;
	
	private IGuild guild;
	
	public DiscordGroupsPermissions(GuildUser user){
		this.user = user.getUser();
		this.guild = user.getGuild();
	}
	
	public static DiscordGroupsPermissions getUserPermissions(GuildUser user){
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
				if(hasRecursive(permsPerm)){
					for(String recursivePerm: getRecursive(permsPerm)){
						if(recursivePerm.equals(perm)){
							hasPerm = true;
						}
					}
				} else {
					if(permsPerm.equals(perm) || permsPerm.equals("*")){
						hasPerm = true;
					}
				}
			}
		}
		return hasPerm;
	}
	
	public void setLocalPerms(String[] perms){
		this.perms = perms;
	}
	
	/**
	 * If the perm ends in a * then this get's all the perms that that star contains.
	 * 
	 * Make sure to call hasRecursive() or handle null from it not ending in a *.
	 * 
	 * @param perm The perm to get recursive perms from.
	 * @return a String[] containing the recursive perms.
	 */
	private String[] getRecursive(String perm){
		if(!hasRecursive(perm)){
			return null;
		} else {
			ArrayList<String> rawRecursive = new ArrayList<String>();
			String[] permSplit = perm.split(".");
			int size = permSplit.length;
			for(String allPerm: allPerms){
				String[] split = allPerm.split(allPerm);
				if(split.length >= size){
					boolean matches = true;
					for(int i = 0; i <= size; i++){
						if(!permSplit[i].equals(split[i])){
							matches = false;
						}
					}
					if(matches){
						rawRecursive.add(allPerm);
					}
				}
			}
			String[] finalRecirsive = new String[rawRecursive.size()];
			finalRecirsive = rawRecursive.toArray(finalRecirsive);
			return finalRecirsive;
		}
	}
	
	/**
	 * This just checks if it ends in a *.
	 * 
	 * @param perm The perm to check
	 * @return if it has recursive perms
	 */
	private boolean hasRecursive(String perm){
		if(perm.substring(perms.length - 2).equals("*")){
			return true;
		} else {
			return false;
		}
	}
	
	
	public static void updatePerms(GuildUser user, DiscordGroups instance){
		
	}
}
