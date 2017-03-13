package com.weeryan17.dgs.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Level;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.weeryan17.dgs.DiscordGroups;
import com.weeryan17.dgs.util.Sync;

public class SocketTimer {
	DiscordGroups instance;
	ServerSocket serverSocket;
	Sync sync;

	public SocketTimer(DiscordGroups instance, int port) {
		this.instance = instance;
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			instance.getLogger().log("Error screating server socket", Level.SEVERE, e, false);
			System.exit(1);
		}
		sync = new Sync(instance);
	}
	
	public void initSocket() {
		while (true) {
			Socket socket = null;
			try {
				socket = serverSocket.accept();
			} catch (IOException e) {
				instance.getLogger().log("Server socket generated an io Exception", Level.SEVERE, e, false);
				System.exit(1);
			}
			
			try {
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				Object ob = in.readObject();
				if (ob instanceof String[][]) {
					String[][] stuff = (String[][]) ob;
					String key = stuff[0][0];
					String process = stuff[0][1];
					Sheet keys = instance.getStorage().getKeysSheet();
					Row row = keys.getRow(keys.getFirstRowNum());
					boolean hasKey = false;
					int colum = -1;
					for(Cell cell : row){
						if(cell.getCellTypeEnum().equals(CellType.STRING)){
							String cellValue = cell.getStringCellValue();
							if(cellValue.equals(key)){
								hasKey = true;
								colum = cell.getAddress().getColumn();
							}
						}
					}
					if(hasKey){
						String[][] values = Arrays.copyOfRange(stuff, 1, stuff.length);
						if(process.equals(instance.getProperties().getProperty("userSyncProcess"))){
							instance.getLogger().log("User sync process activated from ip " + socket.getRemoteSocketAddress(), true);
							Row guildIds = keys.getRow(keys.getFirstRowNum() + 1);
							sync.syncUsers(key, values, guildIds.getCell(colum).getStringCellValue());
						} else if (process.equals(instance.getProperties().getProperty("roleSyncProcess"))){
							instance.getLogger().log("Role sync process activated from ip " + socket.getRemoteSocketAddress(), true);
						} else {
							instance.getLogger().log("Recived an unknow process value from the ip " + socket.getRemoteSocketAddress(), Level.WARNING, true);
						}
					} else {
						instance.getLogger().log("Recived invalid key from the ip " + socket.getRemoteSocketAddress(), Level.WARNING, true);
					}
				} else {
					instance.getLogger().log("Object recived that wasn't a string list from ip " + socket.getRemoteSocketAddress(), Level.WARNING, true);
				}
				socket.close();
				in.close();
			} catch (ClassNotFoundException e) {
				instance.getLogger().log("Recived a class not found exception on socket", Level.SEVERE, e, false);
			} catch (IOException e) {
				instance.getLogger().log("Recived a IO exception on socket", Level.SEVERE, e, false);
			}
			
		}
	}

}
