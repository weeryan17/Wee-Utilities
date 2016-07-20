package com.weeryan17.bounty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;

public class BountyCommand implements CommandExecutor {
  Bounty instance;
  
  public BountyCommand(Bounty instance){
    this.instance = instance;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
	  if(Bounty.gui){
		if(sender.hasPermission("bounty.gui")){
			
		}
	  } else {
		  if(args.length == 0){
			  this.help(sender);
		  } else if(args.length == 1){
			  switch(args[0].toLowerCase()){
			  case "reload":{
				  instance.reloadPluginConfig();
			  }
			  break;
			  case "top":{
				  if(sender.hasPermission("bounty.check.top")){
					  this.loadTopMenu(sender);
				  } else {
					  sender.sendMessage(ChatColor.RED + "You don't have permission to veiw the top bountys");
				  }
			  }
			  break;
			  case "check":{
				  if(sender.hasPermission("bounty.check.self")){
					  if(instance.getPlayerBountyConfig().contains(sender.getName())){
						  sender.sendMessage(ChatColor.BLUE + "You currntly have a bounty of " + ChatColor.GOLD + "$" + String.valueOf(instance.getPlayerBountyConfig().get(sender.getName() + ".bounty")));
					  } else {
						  sender.sendMessage(ChatColor.BLUE + "No one has ever placed a bounty on you");
					  }
				  } else {
					  sender.sendMessage(ChatColor.RED + "You don't have permission to check your own bounty");
				  }
			  }
			  break;
			  case "addbounty":{
				  if(sender.hasPermission("bounty.add")){
					  sender.sendMessage(ChatColor.RED + "Incorect ussage: /bounty addBounty [player] [value]");
				  } else {
					  sender.sendMessage(ChatColor.RED + "You don't have permission to add a bounty to someone.");
				  }
			  }
			  break;
			  default:{
				  sender.sendMessage(ChatColor.RED + "Unknown sub command");
			  }
			  }
		  } else if(args.length == 2){
			  switch(args[0].toLowerCase()){
			  case "check":{
				  if(sender.hasPermission("bounty.check.others")){
					  @SuppressWarnings("deprecation")
					  OfflinePlayer p = Bukkit.getServer().getOfflinePlayer(args[1]);
					  if(p != null){
						  if(instance.getPlayerBountyConfig().contains(p.getName())){
							  sender.sendMessage(ChatColor.RED + p.getName() + ChatColor.BLUE + " currently has a bounty of " + ChatColor.GOLD + "$" + String.valueOf(instance.getConfig().getDouble(p.getName() + ".bounty")));
						  } else {
							  sender.sendMessage(ChatColor.RED + p.getName() + ChatColor.BLUE + " has never had a bounty set on them");
						  }
					  } else {
						  sender.sendMessage(ChatColor.RED + "Invalid players check caps");
					  }
				  } else {
					  sender.sendMessage(ChatColor.RED + "You don't have permission to check other players bounty");
				  }
			  }
			  break;
			  case "addboutny":{
				  if(sender.hasPermission("bounty.add")){
					  sender.sendMessage(ChatColor.RED + "Incorect ussage: /bounty addBounty [player] [value]");
				  } else {
					  sender.sendMessage(ChatColor.RED + "You don't have permission to add a bounty to someone.");
				  }
			  }
			  break;
			  default :{
				  sender.sendMessage(ChatColor.RED + "Invalid sub command or to many args");
			  }
			  }
		  } else if(args.length == 3){
			  switch(args[0].toLowerCase()){
			  case "addbounty":{
				  if(sender.hasPermission("bounty.add")){
					  @SuppressWarnings("deprecation")
					OfflinePlayer p = Bukkit.getOfflinePlayer(args[1]);
					  if(p != null){
						  if(p.getName().equals(sender.getName())){
							  sender.sendMessage(ChatColor.RED + "You can't put a bounty on yourself.");
						  } else {
							  if(this.isNumeric(args[2])){
								  double value = Double.parseDouble(args[2]);
								  try {
									if(Economy.hasEnough(sender.getName(), BigDecimal.valueOf(value))){
										boolean exists = instance.getPlayerBountyConfig().contains(p.getName());
										double existingValue = exists == false ? 0 : instance.getPlayerBountyConfig().getDouble(p.getName() + ".bounty");
										double total = existingValue + value;
										if(total <= Bounty.max || Bounty.max == -1){
											instance.getPlayerBountyConfig().set(p.getName(), total);
											Economy.substract(sender.getName(), BigDecimal.valueOf(value));
											instance.savePlayerBountyConfig();
											if(Bounty.chat){
												Bukkit.broadcastMessage(ChatColor.AQUA + "[" + ChatColor.GREEN + "Bounty" + ChatColor.AQUA + "] " + ChatColor.RED + sender.getName() + ChatColor.BLUE + " has added " + ChatColor.GOLD + "$" + String.valueOf(value) + ChatColor.BLUE + " to the bounty of " + ChatColor.RED + p.getName());
											}
										} else {
											sender.sendMessage(ChatColor.RED + "That much money whould excede the server limit");
										}
									}
								} catch (ArithmeticException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (UserDoesNotExistException e) {
									// TODO Auto-generated catch block
									sender.sendMessage(ChatColor.RED + "Sonehow you don't exist");
									e.printStackTrace();
								} catch (NoLoanPermittedException e) {
									sender.sendMessage(ChatColor.RED + "Money is not allowed to be subtracted from your account");
									e.printStackTrace();
								}
							  } else {
								  sender.sendMessage(ChatColor.RED + "Incorect ussage: /bounty addBounty [player] [value]");
							  }
						  }
					  } else {
						  sender.sendMessage(ChatColor.RED + "Invalid player");
					  }
				  } else {
					  sender.sendMessage(ChatColor.RED + "You don't have permission to add a bounty to someone.");
				  }
			  }
			  break;
			  default :{
				  sender.sendMessage(ChatColor.RED + "Invalid sub command or to many args");
			  }
			  }
		  }
	  }
	  return false;
  }
  
  public void loadTopMenu(CommandSender sender) {
	  ArrayList<OfflinePlayer> all = new ArrayList<OfflinePlayer>();
	  OfflinePlayer[] arrayOfOfflinePlayer;
	  int j = (arrayOfOfflinePlayer = Bukkit.getServer().getOfflinePlayers()).length;
	  for (int i = 0; i < j; i++) {
    	OfflinePlayer p = arrayOfOfflinePlayer[i];
    	all.add(p);
	  }
	  HashMap<String, BigDecimal> values = new HashMap<String, BigDecimal>();
    	for (OfflinePlayer p : all) {
    	if (p.getName() != null) {
    	  if (this.instance.getPlayerBountyConfig().contains(p.getName())) {
        		double amount = this.instance.getPlayerBountyConfig().getDouble(p.getName() + ".bounty");
        		BigDecimal bigAmount = BigDecimal.valueOf(amount);
        		values.put(p.getName(), bigAmount);
        	} else {
        		double amount = 0.0D;
        		BigDecimal bigAmount = BigDecimal.valueOf(amount);
        		values.put(p.getName(), bigAmount);
        	}
      	}
    }
    ArrayList<Map.Entry<String, BigDecimal>> list = arrageMap(values);
    for (int i = 0; (i <= 10) && (i != list.size()); i++) {
    	Entry<String, BigDecimal> map = list.get(i);
    	sender.sendMessage(ChatColor.GREEN + String.valueOf(i) + ". " + ChatColor.RED + map.getKey() + ChatColor.BLUE + " is currently valued at " + ChatColor.GOLD + "$" + String.valueOf(((BigDecimal)map.getValue()).doubleValue()));
    	}
  	}
  
  public ArrayList<Map.Entry<String, BigDecimal>> arrageMap(HashMap<String, BigDecimal> map) {
	  ArrayList<Map.Entry<String, BigDecimal>> sorted = new ArrayList<Map.Entry<String, BigDecimal>>();
	  for (int i = 0; i <= Bukkit.getServer().getOfflinePlayers().length - 1; i++) {
		  Map.Entry<String, BigDecimal> maxEntry = null;
		  for (Map.Entry<String, BigDecimal> entry : map.entrySet()) {
			  if ((maxEntry == null) || (((BigDecimal)entry.getValue()).compareTo((BigDecimal)maxEntry.getValue()) > 0)) {
				  maxEntry = entry;
			  }
		  }
		  map.remove(maxEntry.getKey());
		  sorted.add(maxEntry);
	  }
	return sorted;
  	}
  
  	public boolean isNumeric(String str){
  		try {
  			@SuppressWarnings("unused")
			double d = Double.parseDouble(str);
  		} catch (NumberFormatException nfe) {
  			return false;
  		}
  		return true;
  	}
  
  	public void help(CommandSender sender) {
  		sender.sendMessage(ChatColor.GOLD + "Bounty help menu");
  		if (sender.hasPermission("bounty.add")) {
  			sender.sendMessage(ChatColor.BLUE + "/bounty addbounty <name> <value> : Add a bounty to someone");
  		}
  		if (sender.hasPermission("bounty.check.others")) {
  			sender.sendMessage(ChatColor.BLUE + "/bounty check <name> : checks a players bounty");
  		}
  		if (sender.hasPermission("bounty.check.self")) {
  			sender.sendMessage(ChatColor.BLUE + "/bounty check : checks your bounty");
  		}
  		if (sender.hasPermission("bounty.check.top")) {
  			sender.sendMessage(ChatColor.BLUE + "/bounty top : shows who has the highest bountys");
  		}
  		if (sender.hasPermission("bounty.reload")) {
  			sender.sendMessage(ChatColor.BLUE + "/bounty reload : reloads the main config");
  		}
  	}
}
