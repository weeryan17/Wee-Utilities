package com.weeryan17.reward.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.weeryan17.reward.RewardChest;
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

	@Override
	public boolean sub() {
		return false;
	}

}
