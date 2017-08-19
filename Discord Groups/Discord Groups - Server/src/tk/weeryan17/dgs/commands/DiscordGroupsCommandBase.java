package tk.weeryan17.dgs.commands;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;

public interface DiscordGroupsCommandBase {
	public void onCommand(String[] args, IChannel channel, IUser sender);
}
