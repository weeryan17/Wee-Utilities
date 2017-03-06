package com.weeryan17.dgs.commands.admin;

import com.weeryan17.dgs.commands.DiscordGroupsCommandBase;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

public class PinCommand implements DiscordGroupsCommandBase {

	@Override
	public void onCommand(String[] args, IChannel channel, IUser sender) {
		if(args.length == 0){
			channel.sendMessage(sender.mention() + " Usage: ^pin <id>");
		} else if(args.length == 1) {
			IMessage message = channel.getMessageByID(args[0]);
			if(message != null){
				channel.pin(message);
				channel.getMessageHistory().getLatestMessage().delete();
			} else {
				channel.sendMessage(sender.mention() + " We chouldn't find that message");
			}
		} else {
			channel.sendMessage(sender.mention() + " Usage: ^pin <id>");
		}
	}

}
