package com.weeryan17.dgs.commands.admin;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.weeryan17.dgs.DiscordGroups;
import com.weeryan17.dgs.commands.DiscordGroupsCommandBase;
import com.weeryan17.dgs.permissions.DiscordGroupsPermissions;
import com.weeryan17.dgs.util.GuildUser;

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
		DiscordGroupsPermissions perms = new DiscordGroupsPermissions(new GuildUser(sender, channel.getGuild()));
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
		} else if (args.length != 4) {
			String add;
			String group;
			switch (args[0].toLowerCase()) {
			case "add": {
				add = "add";
			}
				break;
			case "remove": {
				add = "remove";
			}
				break;
			default: {
				add = "<add/remove>";
			}

				switch (args[1].toLowerCase()) {
				case "user": {
					group = "user";
				}
					break;
				case "group": {
					group = "group";
				}
					break;
				default: {
					group = "<user/group>";
				}
				}
				EmbedBuilder builder = instance.getMessageUtil().getBaseEmbed(sender, channel);
				builder.appendField("Usage", "```^permissions" + add + group + "<Id> <permission>```", false);
				channel.sendMessage(sender.mention() + " incorect usage", builder.build());

			}
		} else {
			boolean add;
			boolean group;
			boolean missingPerm = false;
			String perm = "";
			switch (args[0].toLowerCase()) {
			case "add": {
				if (!perms.hasPerm("dg.perm.add")) {
					missingPerm = true;
					perm = "dg.perm.add";
				}
				add = true;
			}
				break;
			case "remove": {
				if (!perms.hasPerm("dg.perm.remove")) {
					missingPerm = true;
					perm = "dg.perm.remove";
				}
				add = false;
			}
				break;
			default: {
				EmbedBuilder builder = instance.getMessageUtil().getBaseEmbed(sender, channel);
				builder.appendField("Usage", "```^permissions <add/remove> <userID/groupID> <permission>```", false);
				channel.sendMessage(sender.mention() + " incorect usage", builder.build());
				return;
			}
			}

			switch (args[1].toLowerCase()) {
			case "user": {
				group = false;
			}
				break;
			case "group": {
				if (add) {
					if (!perms.hasPerm("dg.perm.group.add")) {
						missingPerm = true;
						perm = "dg.perm.group.add";
					}
				} else {
					if (!perms.hasPerm("dg.perm.group.remove")) {
						missingPerm = true;
						perm = "dg.perm.group.remove";
					}
				}
				group = true;
			}
				break;
			default: {
				EmbedBuilder builder = instance.getMessageUtil().getBaseEmbed(sender, channel);
				builder.appendField("Usage", "```^permissions <add/remove> <userID/groupID> <permission>```", false);
				channel.sendMessage(sender.mention() + " incorect usage", builder.build());
				return;
			}
			}

			Sheet sheet = group ? instance.getStorage().getGuildRoleSheet(channel.getGuild().getLongID())
					: instance.getStorage().getGuildUserSheet(channel.getGuild().getLongID());

			long id = 0;

			if (group) {
				IRole role = channel.getGuild().getRoleByID(args[2]);
				if (role != null) {
					id = role.getLongID();
				} else {
					channel.sendMessage(sender.mention() + " that isn't a valid role!");
					return;
				}
			} else {
				IUser user = channel.getGuild().getUserByID(args[2]);
				if (user != null) {
					id = user.getLongID();
				} else {
					channel.sendMessage(sender.mention() + " that isn't a valid user");
					return;
				}
			}
			if (missingPerm) {
				EmbedBuilder builder = instance.getMessageUtil().getBaseEmbed(sender, channel);
				builder.appendField("Missing perm", "```" + perm + "```", false);
			} else {
				if (add) {
					Row row = sheet.getRow(sheet.getFirstRowNum());
					int column = 0;
					for (Cell cell : row) {
						if (cell.getCellTypeEnum().equals(CellType.STRING)) {
							if (cell.getStringCellValue().equals(id)) {
								column = cell.getColumnIndex();
							}
						}
					}
					for (Row rawRow : sheet) {
						Cell cell = rawRow.getCell(column);
						if (cell.getCellTypeEnum().equals(CellType.BLANK)) {
							cell.setCellValue(args[3]);
						}
					}
					DiscordGroupsPermissions.updatePerms(new GuildUser(sender, channel.getGuild()), instance);
				} else {
					Row row = sheet.getRow(sheet.getFirstRowNum());
					int column = 0;
					for (Cell cell : row) {
						if (cell.getCellTypeEnum().equals(CellType.STRING)) {
							if (cell.getStringCellValue().equals(id)) {
								column = cell.getColumnIndex();
							}
						}
					}
					for (Row rawRow : sheet) {
						Cell cell = rawRow.getCell(column);
						if (cell.getCellTypeEnum().equals(CellType.STRING)) {
							if (cell.getStringCellValue().equals(args[3])) {
								cell.setCellType(CellType.BLANK);
							}
						}
					}
				}
				instance.getStorage().savePermsWorkbook(sheet.getWorkbook());
				DiscordGroupsPermissions.updatePerms(new GuildUser(sender, channel.getGuild()), instance);
			}
		}
	}

}
