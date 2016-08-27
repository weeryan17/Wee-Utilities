package com.weeryan17.sc;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.weeryan17.sc.util.ChatChannel;

public class ChatCommand extends Command {
	Chat instance;
	protected ChatCommand(String name, Chat instnace) {
		super(name);
		this.instance = instnace;
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if(sender instanceof Player){
			if(sender.hasPermission("staffchat." + commandLabel + ".use")){
				if(args.length == 0){
					ChatChannel channel = ChatChannel.getChannelByAbreviation(commandLabel);
					String name = channel.getName().toString();
					UUID uuid = ((Player) sender).getUniqueId();
					if(instance.getPlayerDataConfig().contains(uuid.toString() + ".channel")){
						if(instance.getPlayerDataConfig().getString(uuid.toString() + ".channel").equals(name)){
							instance.getPlayerDataConfig().set(uuid.toString() + ".channel", "");
							sender.sendMessage("You left " + ChatColor.GREEN + name);
						} else {
							instance.getPlayerDataConfig().set(uuid.toString() + ".channel", name);
							sender.sendMessage("You joined " + ChatColor.GREEN + name);
						}
					} else {
						instance.getPlayerDataConfig().set(uuid.toString() + ".channel", name);
						sender.sendMessage("You joined " + ChatColor.GREEN + name);
					}
					instance.savePlayerDataConfig();
				} else {
					ChatChannel channel = ChatChannel.getChannelByAbreviation(commandLabel);
					String message = "";
					for(String string : args){
						message = message + " " + string;
					}
					channel.broadcastMessage((Player) sender, message);
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You don't have permission to use this command");
			}
		} else {
			sender.sendMessage(ChatColor.RED + "Only players are alowed to enter chats");
		}
		
		return false;
	}

}
