package com.weeryan17.sww;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WerewolfCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length == 0){
			sender.sendMessage(ChatColor.YELLOW + "__________Werewolf__________");
			sender.sendMessage("Created by " + ChatColor.DARK_RED + "weeryan17" + ChatColor.WHITE + ".");
			sender.sendMessage("");
			
		}
		return true;
	}
	
}
