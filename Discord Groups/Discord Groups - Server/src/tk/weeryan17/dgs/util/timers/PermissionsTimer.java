package tk.weeryan17.dgs.util.timers;

import java.util.TimerTask;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import tk.weeryan17.dgs.DiscordGroups;
import tk.weeryan17.dgs.permissions.DiscordGroupsPermissions;
import tk.weeryan17.dgs.util.GuildUser;

public class PermissionsTimer extends TimerTask {
	DiscordGroups instance;

	public PermissionsTimer(DiscordGroups instance) {
		this.instance = instance;
	}

	@Override
	public void run() {
		instance.getLogger().log("Updating permissions in case event missed something", false);
		for (IGuild guild : instance.client.getGuilds()) {
			Long guildId = guild.getLongID();
			for (IUser user : guild.getUsers()) {
				Sheet userSheet = instance.getStorage().getGuildUserSheet(guildId);
				Row userRow = userSheet.getRow(userSheet.getFirstRowNum());
				boolean hasUser = false;
				for (Cell cell : userRow) {
					if (cell.getCellTypeEnum().equals(CellType.STRING)) {
						if (cell.getStringCellValue().equals(user.getLongID())) {
							hasUser = true;
						}
					}
				}

				if (!hasUser) {
					new DiscordGroupsPermissions(GuildUser.getGuildUser(user, guild));
					int firstBlankCell = -1;
					for (Cell cell : userRow) {
						if (cell.getCellTypeEnum().equals(CellType.BLANK) && firstBlankCell != -1) {

						}
					}
				} else {
					if (DiscordGroupsPermissions.getUserPermissions(GuildUser.getGuildUser(user, guild)) == null) {
						new DiscordGroupsPermissions(GuildUser.getGuildUser(user, guild));
					}
				}
			}

			for (@SuppressWarnings("unused")
			IRole roles : guild.getRoles()) {
				// TODO How haven't I done this yet
			}
		}
	}

}
