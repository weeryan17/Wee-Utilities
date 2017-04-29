package com.weeryan17.dgs.permissions;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.weeryan17.dgs.DiscordGroups;
import com.weeryan17.dgs.util.GuildUser;
import com.weeryan17.dgs.util.Storage;

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
		userPerms.put(user, this);
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
		Storage sto = instance.getStorage();
		Sheet personSheet = sto.getGuildUserSheet(user.getGuild().getLongID());
		Sheet groupSheet = sto.getGuildRoleSheet(user.getGuild().getLongID());
		
		ArrayList<Long> permsList = new ArrayList<Long>();
		
		//Start user sheet perm checking.
		Row userIdRow = personSheet.getRow(personSheet.getFirstRowNum());
		int column = 0;
		for(Cell cell: userIdRow){
			if(cell.getCellTypeEnum().equals(CellType.STRING)){
				long value = Long.parseLong(cell.getStringCellValue());
				if(value == user.getUser().getLongID()){
					column = cell.getColumnIndex();
				}
			}
		}
		
		for(Row row: personSheet){
			Cell cell = row.getCell(column);
			if(cell.getCellTypeEnum().equals(CellType.STRING)){
				long value = Long.parseLong(cell.getStringCellValue());
				if(value != user.getUser().getLongID()){
					permsList.add(value);
				}
			}
		}
		
		//Start roles sheet perm checking.
		for(IRole role: user.getGuild().getRolesForUser(user.getUser())){
			Row roleIdRow = groupSheet.getRow(groupSheet.getFirstRowNum());
			column = 0;
			for(Cell cell: roleIdRow){
				if(cell.getCellTypeEnum().equals(CellType.STRING)){
					String value = cell.getStringCellValue();
					if(value.equals(role.getLongID())){
						column = cell.getColumnIndex();
					}
				}
			}
			
			for(Row row: groupSheet){
				Cell cell = row.getCell(column);
				if(cell.getCellTypeEnum().equals(CellType.STRING)){
					long value = Long.parseLong(cell.getStringCellValue());
					if(value != role.getLongID()){
						permsList.add(value);
					}
				}
			}
		}
		String[] perms = new String[permsList.size()];
		perms = permsList.toArray(perms);
		DiscordGroupsPermissions permissions = DiscordGroupsPermissions.getUserPermissions(user);
		permissions.setLocalPerms(perms);
		
	}
}
