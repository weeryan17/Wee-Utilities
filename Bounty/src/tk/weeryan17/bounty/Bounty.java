package tk.weeryan17.bounty;

import tk.weeryan17.bounty.BountyCommand;
import tk.weeryan17.bounty.KillEvents;
import tk.weeryan17.utilities.api.CommandApi;
import tk.weeryan17.utilities.api.ConfigApi;
import tk.weeryan17.utilities.api.PluginMannager;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Bounty
extends JavaPlugin {
    ConfigApi config;
    CommandApi command = new CommandApi();
    static int max;
    static int id;
    static boolean gui;
    static boolean chat;

	public void onEnable() {
		PluginMannager mannager = new PluginMannager();
		id = mannager.registerPlugin(this);
		config = new ConfigApi(id);
        String discription;
        String permission;
        this.config.saveDefaultConfigs("config", "", false);
        if (gui) {
            discription = "Opens up the bounty gui.";
            permission = "weeBouty.GUI";
        } else {
            discription = "Opens up the bounty command help menu.";
            permission = null;
        }
        this.command.registerCommand(id, "/bounty", discription, permission);
        BountyCommand cmd = new BountyCommand(this);
        this.getCommand("bounty").setExecutor(cmd);
        KillEvents kill = new KillEvents(this);
        Bukkit.getServer().getPluginManager().registerEvents(kill, this);
        this.reloadPluginConfig();
    }

    public void reloadPluginConfig() {
        this.config.reloadConfig("config");
        max = this.getMainConfig().getInt("MaxBounty");
        this.getLogger().info(String.valueOf(max));
        gui = this.getMainConfig().getBoolean("Gui");
        chat = this.getMainConfig().getBoolean("Chat");
    }

    public FileConfiguration getMainConfig() {
        return this.config.config("config", "");
    }

    public void saveMainConfig() {
        this.config.saveConfigs("config", "");
    }

    public FileConfiguration getPlayerBountyConfig() {
        return this.config.config("player data", "player");
    }

    public void savePlayerBountyConfig() {
        this.config.saveConfigs("player data", "player");
    }
}