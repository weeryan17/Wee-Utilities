package com.weeryan17.dgs.util;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import com.weeryan17.dgs.DiscordGroups;

public class SystemTrayUtil {
	
	SystemTray tray;
	public static boolean hasTray = false;
	TrayIcon icon;
	
	public SystemTrayUtil(DiscordGroups instance) {
		if (SystemTray.isSupported()) {
			instance.getLogger().log("System tray is supported. Using!", true);
			PopupMenu popup = new PopupMenu();

			tray = SystemTray.getSystemTray();
			hasTray = true;

			java.awt.Image img = null;
			try {
				img = ImageIO.read(new File("C:/Users/developer/Dropbox/discordgroupstray.png"));
			} catch (IOException e) {
				instance.getLogger().log("Choudln't read image", Level.SEVERE, e, false);
			}
			icon = new TrayIcon(img);

			MenuItem logItem = new MenuItem("Logs");

			popup.add(logItem);

			icon.setPopupMenu(popup);

			try {
				tray.add(icon);
			} catch (AWTException e) {
				instance.getLogger().log("Error setting tray icon", Level.SEVERE, e, false);
			}

		} else {
			instance.getLogger().log("System tray not supported. disableing system tray.", true);
		}
	}
	
	public boolean hasTray(){
		return hasTray;
	}
	
	public TrayIcon getIcon(){
		return icon;
	}
}
