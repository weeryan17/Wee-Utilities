package tk.weeryan17.dgs.commands.admin;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;
import tk.weeryan17.dgs.DiscordGroups;
import tk.weeryan17.dgs.commands.DiscordGroupsCommandBase;
import tk.weeryan17.dgs.permissions.DiscordGroupsPermissions;
import tk.weeryan17.dgs.permissions.PermissionsResponce;
import tk.weeryan17.dgs.util.GuildUser;

public class PermissionsCommand implements DiscordGroupsCommandBase {
	DiscordGroups instance;

	public PermissionsCommand(DiscordGroups instance) {
		this.instance = instance;
	}

	@Override
	public void onCommand(String[] args, IChannel channel, IUser sender) {
		channel.setTypingStatus(true);
		if (!instance.permsReady) {
			channel.sendMessage("Perms arn't ready. Please retry in a little bit");
			return;
		}
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
						if (DiscordGroupsPermissions
								.getUserPermissions(GuildUser.getGuildUser(sender, channel.getGuild()))
								.hasPerm("dg.perm.add")) {
							PermissionsResponce responce = this.addUserPermissions(channel.getGuild(), user, args[3]);
							if (responce.getSucesfull()) {
								this.sendSucessfulUser(true, args[3], channel, user, sender);
							} else {
								channel.sendMessage(sender.mention()
										+ " an unknow error ocured. Please report this to the developers.");
								channel.setTypingStatus(false);
							}
						} else {
							channel.sendMessage(sender.mention() + " missing permission: ```dg.perm.add```");
							channel.setTypingStatus(false);
						}
					} else {
						if (DiscordGroupsPermissions
								.getUserPermissions(GuildUser.getGuildUser(sender, channel.getGuild()))
								.hasPerm("dg.perm.remove")) {
							PermissionsResponce responce = this.removeUserPermissions(channel.getGuild(), user,
									args[3]);
							if (responce.getSucesfull()) {
								this.sendSucessfulUser(false, args[3], channel, user, sender);
							} else {
								switch (responce.getMessage()) {
								case "non-existant-user": {
									channel.sendMessage(
											sender.mention() + " that user doesn't have any perms to remove");
								}
									break;
								case "non-existant-perm": {
									channel.sendMessage(
											sender.mention() + " that user doesn't have the perm `" + args[3] + "`");
								}
								}
								channel.setTypingStatus(false);
							}
						} else {
							channel.sendMessage(sender.mention() + " missing permission: ```dg.perm.remove```");
							channel.setTypingStatus(false);
						}
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
						if (DiscordGroupsPermissions
								.getUserPermissions(GuildUser.getGuildUser(sender, channel.getGuild()))
								.hasPerm("dg.perm.group.add")) {
							PermissionsResponce responce = this.addRolePermissions(channel.getGuild(), role, args[3]);
							if (responce.getSucesfull()) {
								this.sendSucessfulRole(true, args[3], channel, role, sender);
							} else {
								channel.sendMessage(sender.mention()
										+ " an unknow error ocured. Please report this to the developers.");
								channel.setTypingStatus(false);
							}
						} else {
							channel.sendMessage(sender.mention() + " missing permission: ```dg.perm.group.add```");
							channel.setTypingStatus(false);
						}
					} else {
						if (DiscordGroupsPermissions
								.getUserPermissions(GuildUser.getGuildUser(sender, channel.getGuild()))
								.hasPerm("dg.perm.group.remove")) {
							PermissionsResponce responce = this.removeRolePermissions(channel.getGuild(), role,
									args[3]);
							if (responce.getSucesfull()) {
								this.sendSucessfulRole(false, args[3], channel, role, sender);
							} else {
								switch (responce.getMessage()) {
								case "non-existant-user": {
									channel.sendMessage(
											sender.mention() + " that role doesn't have any perms to remove");
								}
									break;
								case "non-existant-perm": {
									channel.sendMessage(
											sender.mention() + " that role doesn't have the perm `" + args[3] + "`");
								}
								}
								channel.setTypingStatus(false);
							}
						} else {
							channel.sendMessage(sender.mention() + " missing permission: ```dg.perm.group.remove```");
							channel.setTypingStatus(false);
						}
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

	public void sendSucessfulRole(boolean add, String perm, IChannel channel, IRole role, IUser sender) {
		EmbedBuilder builder = instance.getMessageUtil().getBaseEmbed(sender, channel);
		if (add) {
			builder.appendDesc("Added permission");
		} else {
			builder.appendDesc("Removed permission");
		}
		builder.appendField("Role", "```" + role.getName() + "```", true);
		builder.appendField("Permission", "```" + perm + "```", true);
		for (IUser user : channel.getGuild().getUsersByRole(role)) {
			GuildUser guildUser = GuildUser.getGuildUser(user, channel.getGuild());
			DiscordGroupsPermissions.updatePerms(guildUser, instance);
		}
		String[] permsList = new String[0];
		StringBuilder sb = new StringBuilder();
		for (String perms : permsList) {
			sb.append(perms + "\n");
		}
		builder.appendField("New Permissions", "```" + sb.toString() + "```", false);
		channel.sendMessage(builder.build());
		channel.setTypingStatus(false);
	}

	public void sendSucessfulUser(boolean add, String perm, IChannel channel, IUser user, IUser sender) {
		EmbedBuilder builder = instance.getMessageUtil().getBaseEmbed(sender, channel);
		if (add) {
			builder.appendDesc("Added permission");
		} else {
			builder.appendDesc("Removed permission");
		}
		builder.appendField("User", "```" + user.getName() + "#" + user.getDiscriminator() + "```", true);
		builder.appendField("Permission", "```" + perm + "```", true);
		GuildUser guildUser = GuildUser.getGuildUser(user, channel.getGuild());
		DiscordGroupsPermissions.updatePerms(guildUser, instance);
		String[] permsList = DiscordGroupsPermissions.getUserPermissions(guildUser).getPerms();
		StringBuilder sb = new StringBuilder();
		for (String perms : permsList) {
			sb.append(perms + "\n");
		}
		builder.appendField("New Permissions", "```" + sb.toString() + "```", false);
		channel.sendMessage(builder.build());
		channel.setTypingStatus(false);
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
			for (Row row : sheet) {
				Cell cell = row.getCell(column);
				if (cell.getCellTypeEnum().equals(CellType.BLANK)) {
					cell.setCellValue(permission);
					instance.getStorage().savePermsWorkbook(sheet.getWorkbook());
					return new PermissionsResponce(true, "sucess");
				}
			}
		} else {
			for (Cell cell : firstRow) {
				if (cell.getCellTypeEnum().equals(CellType.STRING)) {
					cell.setCellValue(String.valueOf(user.getLongID()));
					column = cell.getColumnIndex();
					Row row = sheet.getRow(sheet.getFirstRowNum() + 1);
					Cell cells = row.getCell(column);
					cells.setCellValue(permission);
					instance.getStorage().savePermsWorkbook(sheet.getWorkbook());
					return new PermissionsResponce(true, "sucess");
				}
			}
		}
		return new PermissionsResponce(false, "unknown");
	}

	public PermissionsResponce removeUserPermissions(IGuild guild, IUser user, String permission) {
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
		if (column == -1) {
			return new PermissionsResponce(false, "non-existant-user");
		} else {
			for (Row row : sheet) {
				Cell permCell = row.getCell(column);
				if (permCell.getCellTypeEnum().equals(CellType.STRING)) {
					String perm = permCell.getStringCellValue();
					if (perm.equals(permission)) {
						permCell.setCellValue("");
						permCell.setCellType(CellType.BLANK);
						instance.getStorage().savePermsWorkbook(sheet.getWorkbook());
						return new PermissionsResponce(true, "sucess");
					}
				}
			}
			return new PermissionsResponce(false, "non-existant-perm");
		}
	}

	public PermissionsResponce addRolePermissions(IGuild guild, IRole role, String permission) {
		Sheet sheet = instance.getStorage().getGuildRoleSheet(guild.getLongID());
		Row firstRow = sheet.getRow(sheet.getFirstRowNum());
		int column = -1;
		for (Cell cell : firstRow) {
			if (cell.getCellTypeEnum().equals(CellType.STRING)) {
				String stringId = cell.getStringCellValue();
				Long id = Long.parseLong(stringId);
				if (role.getLongID() == id) {
					column = cell.getColumnIndex();
				}
			}
		}
		if (column == -1) {
			for (Row row : sheet) {
				Cell cell = row.getCell(column);
				if (cell.getCellTypeEnum().equals(CellType.BLANK)) {
					cell.setCellValue(permission);
					instance.getStorage().savePermsWorkbook(sheet.getWorkbook());
					return new PermissionsResponce(true, "sucess");
				}
			}
		} else {
			for (Cell cell : firstRow) {
				if (cell.getCellTypeEnum().equals(CellType.BLANK)) {
					cell.setCellValue(String.valueOf(role.getLongID()));
					column = cell.getColumnIndex();
					Row row = sheet.getRow(sheet.getFirstRowNum() + 1);
					Cell cells = row.getCell(column);
					cells.setCellValue(permission);
					instance.getStorage().savePermsWorkbook(sheet.getWorkbook());
					return new PermissionsResponce(true, "sucess");
				}
			}
		}
		return new PermissionsResponce(false, "unknown");
	}

	public PermissionsResponce removeRolePermissions(IGuild guild, IRole role, String permission) {
		Sheet sheet = instance.getStorage().getGuildRoleSheet(guild.getLongID());
		Row firstRow = sheet.getRow(sheet.getFirstRowNum());
		int column = -1;
		for (Cell cell : firstRow) {
			if (cell.getCellTypeEnum().equals(CellType.STRING)) {
				String stringId = cell.getStringCellValue();
				Long id = Long.parseLong(stringId);
				if (role.getLongID() == id) {
					column = cell.getColumnIndex();
				}
			}
		}
		if (column == -1) {
			return new PermissionsResponce(false, "non-existant-user");
		} else {
			for (Row row : sheet) {
				Cell permCell = row.getCell(column);
				if (permCell.getCellTypeEnum().equals(CellType.STRING)) {
					String perm = permCell.getStringCellValue();
					if (perm.equals(permission)) {
						permCell.setCellValue("");
						permCell.setCellType(CellType.BLANK);
						instance.getStorage().savePermsWorkbook(sheet.getWorkbook());
						return new PermissionsResponce(true, "sucess");
					}
				}
			}
			return new PermissionsResponce(false, "non-existant-perm");
		}
	}
}
