package com.weeryan17.elv;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.weeryan17.utilities.api.ConfigApi;
import com.weeryan17.utilities.api.PluginMannager;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.server.v1_10_R1.PacketDataSerializer;
import net.minecraft.server.v1_10_R1.PacketPlayOutCustomPayload;

public class Elevators extends JavaPlugin {
	ConfigApi confApi;
	static int id;
	public void onEnable(){
		id = new PluginMannager().registerPlugin(this);
		confApi = new ConfigApi(id);
		Events events = new Events(this);
		Bukkit.getPluginManager().registerEvents(events, this);
	}
	
	public boolean isElevatorPlate(ItemStack item){
		String name = item.getItemMeta().getDisplayName();
		ArrayList<String> itemLore = (ArrayList<String>) item.getItemMeta().getLore();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("Place this down to make an elevator");
		Material mat = item.getType();
		if(mat.equals(Material.STONE_PLATE) && name.equals("§6Elevator plate") && lore.equals(itemLore)){
			return true;
		} else{
			return false;
		}
	}
	
	public FileConfiguration getElevatorsConfig(){
		return confApi.config("Elevators", "");
	}
	
	public void saveElevatorsConfig(){
		confApi.saveConfigs("Elevators", "");
	}
	
    public void openBook(ItemStack book, Player p) {
        int slot = p.getInventory().getHeldItemSlot();
        ItemStack old = p.getInventory().getItem(slot);
        p.getInventory().setItem(slot, book);

       ByteBuf buf = Unpooled.buffer(256);
       buf.setByte(0, (byte)0);
       buf.writerIndex(1);

        PacketPlayOutCustomPayload packet = new PacketPlayOutCustomPayload("MC|BOpen", new PacketDataSerializer(buf));
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
        p.getInventory().setItem(slot, old);
    }
}
