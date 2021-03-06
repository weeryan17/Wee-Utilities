package tk.weeryan17.dgc;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.permission.Permission;
import tk.weeryan17.utilities.api.ConfigApi;

public class DiscordGroupsPlugin extends JavaPlugin {
	Socket socket;

	ConfigApi conf;

	InputStream socketInStream;
	ObjectOutputStream socketObjects;
	Permission perm;
	Updater update;
	
	String address = "69.30.176.196"; // Removed from github for security
	// reasons. Although you can
	// probably figure this one out when
	// this is done.
	int port = 8100; // Removed from github for security reasons.

	@SuppressWarnings("deprecation")
	public void onEnable() {
		// Defining everything!
		conf = new ConfigApi(this);
		if (!this.getKeyConfig().contains("Key")) {
			getLogger().info("Key config not found or key isn't set. Please put your key in the config.");
			conf.saveDefaultConfigs("key", "", false);
		}

		RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
		perm = rsp.getProvider();

		// Actual stating stuff goes here
		update = new Updater(this);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, update, 0, 1000L);
	}

	public void onDissable() {
		try {
			socket.close();
		} catch (IOException e) {
			getLogger().log(Level.SEVERE, "Error closing socket!", e);
		}
	}

	public Socket getSocket() {
		return socket;
	}

	public Updater getUpdater() {
		return update;
	}

	public Permission getPermissions() {
		return perm;
	}

	public String getKey() {
		return this.getKeyConfig().getString("Key");
	}

	public FileConfiguration getKeyConfig() {
		return conf.config("key.yml", "");
	}
	
	public String getAddress(){
		return this.address;
	}
	
	public int getPort(){
		return this.port;
	}
}
