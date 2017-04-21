package com.weeryan17.dgs.commands.admin;

import java.util.Timer;
import java.util.TimerTask;

import com.weeryan17.dgs.commands.DiscordGroupsCommandBase;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

public class PinCommand implements DiscordGroupsCommandBase {

	@Override
	public void onCommand(String[] args, IChannel channel, IUser sender) {
		if (args.length == 0) {
			channel.sendMessage(sender.mention() + " Usage: ^pin <id>");
		} else if (args.length == 1) {
			IMessage message = channel.getMessageByID(Long.valueOf(args[0]));
			if (message != null) {
				channel.pin(message);
				new Timer().schedule(new TimerTask(){

					@Override
					public void run() {
						channel.getMessageHistory().getLatestMessage().delete();
					}
					
				}, 1000L);
			} else {
				channel.sendMessage(sender.mention() + " We chouldn't find that message");
			}
		} else {
			channel.sendMessage(sender.mention() + " Usage: ^pin <id>");
		}
	}

}
