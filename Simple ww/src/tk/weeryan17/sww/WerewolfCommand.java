package tk.weeryan17.sww;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

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
					p.sendMessage("You are in the " + ChatColor.GOLD + instance.getWerewolfMannager().getWerewolfByPlayer(p).getClan().getName() + " clan" + ChatColor.WHITE + ".");
				}
			} else {
				for(World world: Bukkit.getWorlds()){
					sender.sendMessage(ChatColor.GOLD + String.valueOf(instance.getWerewolfMannager().getDaysTillMoon(world)) + " days " + ChatColor.WHITE + "till full moon in world " + ChatColor.GOLD + world.getName());
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
			break;
			case "give" :{
				if(sender.hasPermission("ww.command.admin.give")){
					if(args.length == 1){
						this.handleHelp(sender, args);
					} else if(args.length == 2) {
						sender.sendMessage(ChatColor.RED + "Inavlid args /ww give <player> <item>");
					} else if(args.length == 3) {
						Player p = Bukkit.getPlayer(args[1]);
						if(p != null){
							if(instance.getWerewolfMannager().isWerewolfItem(args[2])){
								p.getInventory().addItem(instance.getWerewolfMannager().getWerewolfItem(args[2]));
							} else {
								sender.sendMessage(ChatColor.RED + "Invalid item.");
							}
						} else {
							sender.sendMessage(ChatColor.RED + "Inavlid player.");
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Inavlid args /ww give <player> <item>");
					}
				} else {
					sender.sendMessage(ChatColor.RED + "You don't have permission to give people werewolf items.");
				}
			}
			break;
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
			break;
			case "help" :{
				this.handleHelp(sender, Arrays.copyOfRange(args, 1, args.length));
			}
			break;
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
				sender.sendMessage(ChatColor.GOLD + "/ww toggle <player>: " + ChatColor.WHITE + "Toggles that players werewolf state.");
				sender.sendMessage(ChatColor.GOLD + "/ww give <player> <item>: " + ChatColor.GOLD + "Gives the specified player the pecified item.");
			}
			break;
			case "give" :{
				sender.sendMessage(ChatColor.GOLD + "______________-Werewolf Help Menu-_______________");
				sender.sendMessage(ChatColor.GOLD + "/ww give " + ChatColor.WHITE + "help menu");
				sender.sendMessage(ChatColor.GOLD + "/ww give <player> <item>");
				sender.sendMessage("Current accepted items:");
				if(sender instanceof Player){
					sender.sendMessage("(The name is the thing before the : the rest is just showing you the item.)");
					//TODO add a method in the wee utilities api for hover events
					Player p = (Player) sender;
					
					//Pure Silver Sword
					TextComponent pureSilverSwordMessage = new TextComponent("puresilversword: ");
					pureSilverSwordMessage.setColor(net.md_5.bungee.api.ChatColor.GOLD);
					TextComponent pureSilverSwordText = new TextComponent("Pure Silver Sword");
					pureSilverSwordText.setColor(net.md_5.bungee.api.ChatColor.BLUE);
					String pureSilverSwordJson = instance.convertItemStackToJsonRegular(instance.pureSilverSword);
					BaseComponent[] pureSilverSwordTextHoverComponents = new BaseComponent[]{
							new TextComponent(pureSilverSwordJson)
					};
					pureSilverSwordText.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, pureSilverSwordTextHoverComponents));
					pureSilverSwordMessage.addExtra(pureSilverSwordText);
					p.spigot().sendMessage(pureSilverSwordMessage);
					
					//Normal Silver Sword
					TextComponent normalSilverSwordMessage = new TextComponent("normalsilversword: ");
					normalSilverSwordMessage.setColor(net.md_5.bungee.api.ChatColor.GOLD);
					TextComponent normalSilverSwordText = new TextComponent("Silver Sword");
					normalSilverSwordText.setColor(net.md_5.bungee.api.ChatColor.BLUE);
					String normalSilverSwordJson = instance.convertItemStackToJsonRegular(instance.normalSilverSword);
					BaseComponent[] normalSilverSilverSwordTextHoverComponents = new BaseComponent[]{
						new TextComponent(normalSilverSwordJson)
					};
					normalSilverSwordText.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, normalSilverSilverSwordTextHoverComponents));
					normalSilverSwordMessage.addExtra(normalSilverSwordText);
					p.spigot().sendMessage(normalSilverSwordMessage);
					
					//Pure silver
					TextComponent pureSilverMessage = new TextComponent("puresilver: ");
					pureSilverMessage.setColor(net.md_5.bungee.api.ChatColor.GOLD);
					TextComponent pureSilverText = new TextComponent("Pure Silver");
					pureSilverText.setColor(net.md_5.bungee.api.ChatColor.BLUE);
					String pureSilverJson = instance.convertItemStackToJsonRegular(instance.pureSilver);
					BaseComponent[] pureSilverTextHoverComponents = new BaseComponent[]{
							new TextComponent(pureSilverJson)
					};
					pureSilverText.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, pureSilverTextHoverComponents));
					pureSilverMessage.addExtra(pureSilverText);
					p.spigot().sendMessage(pureSilverMessage);
					
					//Normal Silver
					TextComponent normalSilverMessage = new TextComponent("normalsilver: ");
					normalSilverMessage.setColor(net.md_5.bungee.api.ChatColor.GOLD);
					TextComponent normalSilverText = new TextComponent("Silver");
					normalSilverText.setColor(net.md_5.bungee.api.ChatColor.BLUE);
					String normalSilverJson = instance.convertItemStackToJsonRegular(instance.normalSilver);
					BaseComponent[] normalSilverTextHoverComponents = new BaseComponent[]{
							new TextComponent(normalSilverJson)
					};
					normalSilverText.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, normalSilverTextHoverComponents));
					normalSilverMessage.addExtra(normalSilverText);
					p.spigot().sendMessage(normalSilverMessage);
					
					//Cure Potion
					TextComponent curePotionMessage = new TextComponent("curepotion: ");
					curePotionMessage.setColor(net.md_5.bungee.api.ChatColor.GOLD);
					TextComponent curePotionText = new TextComponent("Cure Potion");
					curePotionText.setColor(net.md_5.bungee.api.ChatColor.GREEN);
					String curePotionJson = instance.convertItemStackToJsonRegular(instance.curePotion);
					BaseComponent[] curePotionTextHoverComponents = new BaseComponent[]{
							new TextComponent(curePotionJson)
					};
					curePotionText.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, curePotionTextHoverComponents));
					curePotionMessage.addExtra(curePotionText);
					p.spigot().sendMessage(curePotionMessage);
					
					//unfinished cure potion
					TextComponent UFcurePotionMessage = new TextComponent("unfinishedcurepotion: ");
					UFcurePotionMessage.setColor(net.md_5.bungee.api.ChatColor.GOLD);
					TextComponent UFcurePotionText = new TextComponent("Unfinished Cure Potion");
					UFcurePotionText.setColor(net.md_5.bungee.api.ChatColor.RED);
					String UFcurePotionJson = instance.convertItemStackToJsonRegular(instance.UFcurePotion);
					BaseComponent[] UFcorePotionTextHoverComponents = new BaseComponent[]{
							new TextComponent(UFcurePotionJson)
					};
					UFcurePotionText.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, UFcorePotionTextHoverComponents));
					UFcurePotionMessage.addExtra(UFcurePotionText);
					p.spigot().sendMessage(UFcurePotionMessage);
				} else {
					sender.sendMessage(ChatColor.RED + "Curently this help menu isn't suported by non-payers due to hover events");
				}
			}
			break;
			case "toggle" :{
				if(sender.hasPermission("ww.command.admin.toggle")){
					sender.sendMessage(ChatColor.GOLD + "______________-Werewolf Help Menu-_______________");
					sender.sendMessage(ChatColor.GOLD + "/ww toggle <player>");
					sender.sendMessage("It toggles the specifiedplayers werewolf state");
				} else {
					sender.sendMessage(ChatColor.RED + "You don't have permission for this command so you don't need to look up a help on it");
				}
			}
			break;
			default :{
				sender.sendMessage(ChatColor.RED + "The help menu your looking for doesn't exist (yet).");
			}
			}
		} else {
			sender.sendMessage(ChatColor.RED + "The help menu doesn't go this deep currently");
		}
	}
}
