package com.weeryan17.gui;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GUICommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(args.length != 1){
				sender.sendMessage("Invalid usage");
			} else {
				ItemStack hoe = new ItemStack(Material.WOOD_HOE, 1, (short)this.getDuribility(Integer.parseInt(args[0])));
				p.getEquipment().setItemInOffHand(hoe);
			}
		} else {
			sender.sendMessage("only players can preform this command");
		}
		return false;
	}
	public int getDuribility(int value){
		return 6 - value;
	}
}
