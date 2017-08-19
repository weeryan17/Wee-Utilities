package tk.weeryan17.gui;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class GUI extends JavaPlugin {
	public void onEnable(){
		Events events = new Events(this);
		Bukkit.getServer().getPluginManager().registerEvents(events, this);
		GUICommand cmd = new GUICommand();
		getCommand("si").setExecutor(cmd);
	}
}
