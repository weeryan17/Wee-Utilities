package tk.weeryan17.reward.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import tk.weeryan17.reward.RewardChest;
import tk.weeryan17.utilities.api.interfaces.BaseCommand;

public class RcCommand implements BaseCommand {
	
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
	public String getCommand() {
		return "rc";
	}

	@Override
	public String getDescription() {
		return "";
	}

	@Override
	public JavaPlugin getPlugin(){ 
		return instance;
	}

}
