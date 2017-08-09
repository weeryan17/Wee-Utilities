package com.weeryan17.reward;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.weeryan17.reward.managers.RewardCommandExecutor;

public class RcCommand implements RewardCommandExecutor {
	
	RewardChest instance;
	public RcCommand(RewardChest instance) {
		this.instance = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		
		return true;
	}
	
	@Override
	public String getUsage() {
		return "";
	}

}
