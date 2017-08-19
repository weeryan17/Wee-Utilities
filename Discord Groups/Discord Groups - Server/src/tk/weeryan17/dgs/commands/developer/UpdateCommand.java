package tk.weeryan17.dgs.commands.developer;

import java.util.ArrayList;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import tk.weeryan17.dgs.DiscordGroups;
import tk.weeryan17.dgs.commands.DiscordGroupsCommandBase;
import tk.weeryan17.dgs.util.Update;

public class UpdateCommand implements DiscordGroupsCommandBase {
	ArrayList<Long> ids;
	DiscordGroups instance;

	public UpdateCommand(DiscordGroups instance) {
		ids = instance.getDevelopersIds();
	}

	@Override
	public void onCommand(String[] args, IChannel channel, IUser sender) {
		if (ids.contains(sender.getLongID())) {
			if (args.length >= 1) {
				channel.sendMessage(sender.mention() + " learn how your own command works");
			}
			new Update(instance).update();
		}
		//TODO get this to work
	}

}
