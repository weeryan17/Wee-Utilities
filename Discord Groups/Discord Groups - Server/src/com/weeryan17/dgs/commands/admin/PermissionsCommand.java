package com.weeryan17.dgs.commands.admin;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.weeryan17.dgs.DiscordGroups;
import com.weeryan17.dgs.commands.DiscordGroupsCommandBase;
import com.weeryan17.dgs.permissions.DiscordGroupsPermissions;
import com.weeryan17.dgs.permissions.PermissionsResponce;
import com.weeryan17.dgs.util.GuildUser;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
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
				this.sendInvlid(args, channel, sender);
				return;
			}
			}
			boolean isuser;
			switch (args[1]) {
			case "user": {
				isuser = true;
			}
				break;
			case "group": {
				isuser = false;
			}
				break;
			default: {
				this.sendInvlid(args, channel, sender);
				return;
			}
			}
			if (isuser) {
				Object obj = instance.getMessageUtil().getUserFromString(args[2], channel);
				if (obj instanceof IUser) {
					IUser user = (IUser) obj;
					if (add) {
						PermissionsResponce responce = this.addUserPermissions(channel.getGuild(), user, args[3]);
						if (responce.getSucesfull()) {
							EmbedBuilder builder = instance.getMessageUtil().getBaseEmbed(sender, channel);
							builder.appendDesc("Added permission");
							builder.appendField("User", user.getName()+"#"+user.getDiscriminator(), true);
							builder.appendField("Permission", args[3], true);
							String[] perms = DiscordGroupsPermissions.getUserPermissions(GuildUser.getGuildUser(user, channel.getGuild())).getPerms();
							StringBuilder sb = new StringBuilder();
							for(String perm: perms){
								sb.append(perm + "\n");
							}
							builder.appendField("New Permissions", sb.toString(), false);
							channel.sendMessage(builder.build());
							channel.setTypingStatus(false);
						} else {
							channel.sendMessage(sender.mention()
									+ " an unknow error ocured. Please report this to the developers.");
						}
					} else {
						this.removeUserPermissions(channel.getGuild(), user, args[3]);
					}
				} else {
					String error = (String) obj;
					switch (error) {
					case "inavlid": {
						channel.sendMessage(sender.mention() + " Invalid user");
						this.sendInvlid(args, channel, sender);
					}
						break;
					case "multiple": {
						channel.sendMessage(sender.mention() + " Mutiple users where found with that name");
						this.sendInvlid(args, channel, sender);
					}
					}
				}
			} else {
				Object obj = instance.getMessageUtil().getRoleFromString(args[2], channel);
				if (obj instanceof IRole) {
					IRole role = (IRole) obj;
					if (add) {
						this.addRolePermissions(channel.getGuild(), role, args[3]);
					} else {
						this.removeRolePermissions(channel.getGuild(), role, args[3]);
					}
				} else {
					String error = (String) obj;
					switch (error) {
					case "inavlid": {
						channel.sendMessage(sender.mention() + " Invalid role");
						this.sendInvlid(args, channel, sender);
					}
						break;
					case "multiple": {
						channel.sendMessage(sender.mention() + " Mutiple roles where found with that name");
						this.sendInvlid(args, channel, sender);
					}
					}
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
					builder.appendField("Usage", "```^permissions " + addString + " <user/group> <Id> <permission>```",
							false);
					channel.sendMessage(builder.build());
					channel.setTypingStatus(false);
					return;
				}
				}
				String addString = add ? "add" : "remove";
				String isUser = user ? "user" : "group";
				builder.appendField("Usage", "```^permissions " + addString + " " + isUser + " <Id> <permission>```",
						false);
				channel.sendMessage(builder.build());
				channel.setTypingStatus(false);
			} else {
				String addString = add ? "add" : "remove";
				builder.appendField("Usage", "```^permissions " + addString + " <user/group> <Id> <permission>```",
						false);
				channel.sendMessage(builder.build());
				channel.setTypingStatus(false);
			}
		}
	}

	public PermissionsResponce addUserPermissions(IGuild guild, IUser user, String permission) {
		Sheet sheet = instance.getStorage().getGuildUserSheet(guild.getLongID());
		Row firstRow = sheet.getRow(sheet.getFirstRowNum());
		int column = -1;
		for (Cell cell : firstRow) {
			if (cell.getCellTypeEnum().equals(CellType.STRING)) {
				String stringId = cell.getStringCellValue();
				Long id = Long.parseLong(stringId);
				if (user.getLongID() == id) {
					column = cell.getColumnIndex();
				}
			}
		}
		if (column != -1) {
			instance.getLogger().log("User was found.", false);
			for (Row row : sheet) {
				Cell cell = row.getCell(column);
				if (cell.getCellTypeEnum().equals(CellType.BLANK)) {
					cell.setCellValue(permission);
					instance.getStorage().savePermsWorkbook(sheet.getWorkbook());
					return new PermissionsResponce(true, "permission `" + permission + "` added without error");
				}
			}
		} else {
			instance.getLogger().log("User wasn't found.", false);
			for(Cell cell: firstRow){
				instance.getLogger().log("Cell type: " + cell.getCellTypeEnum().toString(), false);
				if(cell.getCellTypeEnum().equals(CellType.BLANK)){
					instance.getLogger().log("Empty cell found", false);
					cell.setCellValue(String.valueOf(user.getLongID()));
					instance.getLogger().log("User added", false);
					column = cell.getColumnIndex();
					instance.getLogger().log("Row count: " + sheet.getLastRowNum(), false);
					instance.getLogger().log("Getting 2nd row", false);
					Row row;
					if(sheet.getLastRowNum() == sheet.getFirstRowNum()){
						instance.getLogger().log("Only 1 row found creating more", false);
						row = sheet.createRow(sheet.getFirstRowNum() + 1);
						for(int i = 0; i <= 1000; i++){
							row.createCell(i);
						}
					} else {
						row = sheet.getRow(sheet.getFirstRowNum() + 1);
					}
					instance.getLogger().log("Row: " + row.toString(), false);
					Cell cells = row.getCell(column);
					cells.setCellValue(permission);
					instance.getLogger().log("Permission added", false);
					instance.getLogger().log("Saving stuffs", false);
					instance.getStorage().savePermsWorkbook(sheet.getWorkbook());
					instance.getLogger().log("Done saving stuffs", false);
					return new PermissionsResponce(true, "permission `" + permission + "` added along with user");
				}
			}
		}
		return new PermissionsResponce(false, "unknow");
	}

	public PermissionsResponce removeUserPermissions(IGuild guild, IUser user, String permission) {
		return null;
	}

	public PermissionsResponce addRolePermissions(IGuild guild, IRole role, String permission) {
		return null;
	}

	public PermissionsResponce removeRolePermissions(IGuild guild, IRole role, String permission) {
		return null;
	}
}
