package com.weeryan17.sww;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WerewolfCommand implements CommandExecutor {
	WerewolfPlugin instance;
	public WerewolfCommand(WerewolfPlugin instance){
		this.instance = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length == 0){
			sender.sendMessage(ChatColor.YELLOW + "__________Werewolf__________");
			sender.sendMessage("Created by " + ChatColor.DARK_RED + "weeryan17" + ChatColor.WHITE + ".");
			sender.sendMessage("");
			sender.sendMessage(ChatColor.BLUE + "/ww help:" + ChatColor.WHITE + " This brings up a more detalied help menu");
			sender.sendMessage("");
			if(sender instanceof Player){
				Player p = (Player) sender;
				p.sendMessage(ChatColor.GOLD + String.valueOf(instance.getWerewolfMannager().getDaysTillMoon(p.getWorld())) + " days " + ChatColor.WHITE + "till full moon in your world");
				if(instance.getWerewolfMannager().isWerewolf(p)){
					//Going to be added after Clans are done
				}
			} else {
				for(World world: Bukkit.getWorlds()){
					sender.sendMessage(ChatColor.GOLD + String.valueOf(instance.getWerewolfMannager().getDaysTillMoon(world)) + " days " + ChatColor.WHITE + "till full moon in world " + world.getName());
				}
			}
			
		} else {
			switch(args[0]){
			
			case "admin" :{
				if(args.length == 1){
					this.handleHelp(sender, args);
				} else {
					sender.sendMessage(ChatColor.RED + "Their are no sub commands for /ww admin");
				}
			}
			
			case "toggle" :{
				if(sender.hasPermission("ww.command.admin.toggle")){
					if(args.length == 2){
						Player p = Bukkit.getPlayer(args[1]);
						if(p != null){
							boolean isWolf = instance.getWerewolfMannager().toggleWerewolf(p);
							if(isWolf){
								sender.sendMessage("The player " + p.getName() + " is now a werewolf.");
								p.sendMessage(sender.getName() + " turned you into a werewolf.");
							} else {
								sender.sendMessage("The player " + p.getName() + " is no longer a werewolf.");
								p.sendMessage(sender.getName() + " made it so you are no longer a werewolf.");
							}
						} else {
							sender.sendMessage(ChatColor.RED + "Invalid Player");
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Inavlid usage: /ww toggle <player>");
					}
				} else {
					sender.sendMessage(ChatColor.RED + "You don't have permision to prefourm this command");
				}
			}
			
			case "help" :{
				this.handleHelp(sender, Arrays.copyOfRange(args, 1, args.length));
			}
			
			default :{
				sender.sendMessage("Ivalid sub command");
			}
			
			}
		}
		return true;
	}
	
	public void handleHelp(CommandSender sender, String[] args){
		if(args.length == 0){
			sender.sendMessage(ChatColor.GOLD + "______________-Werewolf Help Menu-_______________");
			sender.sendMessage(ChatColor.GOLD + "/ww: " + ChatColor.WHITE + "Brings up the main menu.");
			sender.sendMessage(ChatColor.GOLD + "/ww help: " + ChatColor.WHITE + "Brings up this menu.");
			sender.sendMessage(ChatColor.GOLD + "/ww help <command>: " + ChatColor.WHITE + "Brings up a more detailed help menu on that command.");
			sender.sendMessage(ChatColor.GOLD + "/ww world: " + ChatColor.WHITE + "Brings up the info on all worlds.");
			sender.sendMessage(ChatColor.GOLD + "/ww world <world>: " + ChatColor.WHITE + "Brings up more spicific werewolf info on the specified world.");
			if(sender.hasPermission("ww.command.admin")){
				sender.sendMessage(ChatColor.GOLD + "/ww admin: " + ChatColor.WHITE + "Brings up the admin help menu.");
			}
		} else if(args.length == 1) {
			switch(args[0]){
			case "admin" :{
				sender.sendMessage(ChatColor.GOLD + "______________-Werewolf Help Menu-_______________");
				sender.sendMessage(ChatColor.GOLD + "");
			}
			}
		} else {
			sender.sendMessage(ChatColor.RED + "The help menu doesn't go this deep currently");
		}
	}
}
