package tk.weeryan17.dgs.commands.developer;

import java.util.ArrayList;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import tk.weeryan17.dgs.DiscordGroups;
import tk.weeryan17.dgs.commands.DiscordGroupsCommandBase;

public class StopCommand implements DiscordGroupsCommandBase {
	DiscordGroups instance;
	ArrayList<Long> ids;

	public StopCommand(DiscordGroups instance) {
		this.instance = instance;
		ids = instance.getDevelopersIds();
	}

	@Override
	public void onCommand(String[] args, IChannel channel, IUser sender) {
		if (ids.contains(sender.getLongID())) {
			instance.getLogger().log("Stopping bot", true);
			System.exit(0);
		}
	}

}
