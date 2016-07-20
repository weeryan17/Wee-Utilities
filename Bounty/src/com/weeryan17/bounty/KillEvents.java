package com.weeryan17.bounty;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import com.weeryan17.bounty.Bounty;
import java.math.BigDecimal;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class KillEvents
implements Listener {
    Bounty instance;

    public KillEvents(Bounty instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onKill(PlayerDeathEvent e) {
        Player p = e.getEntity();
        if (p.getKiller() instanceof Player) {
            this.instance.getLogger().info("A player killed a player");
            Player killer = p.getKiller();
            if (this.instance.getPlayerBountyConfig().contains(p.getName()) && p.getName() != killer.getName() && this.instance.getPlayerBountyConfig().getDouble(String.valueOf(p.getName()) + ".bounty") != 0.0) {
                if (Bounty.chat) {
                    Bukkit.broadcastMessage(ChatColor.AQUA + "[" + ChatColor.GREEN + "Bounty" + ChatColor.AQUA + "] " + ChatColor.RED + killer.getName() + ChatColor.BLUE + " killed " + ChatColor.RED + p.getName() + ChatColor.BLUE + " to recive " + ChatColor.GOLD + "$" + String.valueOf(this.instance.getPlayerBountyConfig().getDouble(p.getName() + ".bounty")));
                }
                try {
                    Economy.add(killer.getName(), BigDecimal.valueOf(this.instance.getPlayerBountyConfig().getDouble(p.getName() + ".bounty")));
                }
                catch (NoLoanPermittedException | UserDoesNotExistException | ArithmeticException e1) {
                    e1.printStackTrace();
                }
                this.instance.getPlayerBountyConfig().set(p.getName() + ".bounty", 0);
                this.instance.savePlayerBountyConfig();
            }
        }
    }
}