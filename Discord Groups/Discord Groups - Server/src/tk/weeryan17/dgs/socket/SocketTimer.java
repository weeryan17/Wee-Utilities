//package tk.weeryan17.dgs.socket;
//
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.Arrays;
//import java.util.logging.Level;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//
//import tk.weeryan17.dgs.DiscordGroups;
//import tk.weeryan17.dgs.util.Sync;
//
//public class SocketTimer {
//	DiscordGroups instance;
//	ServerSocket serverSocket;
//	Sync sync;
//
//	public SocketTimer(DiscordGroups instance, int port) {
//		this.instance = instance;
//		try {
//			serverSocket = new ServerSocket(port);
//		} catch (IOException e) {
//			instance.getLogger().log("Error creating server socket", Level.SEVERE, e, false);
//			System.exit(1);
//		}
//		sync = new Sync(instance);
//	}
//
//	public void initSocket() {
//		while (true) {
//			Socket socket = null;
//			try {
//				socket = serverSocket.accept();
//			} catch (IOException e) {
//				instance.getLogger().log("Server socket generated an io Exception", Level.SEVERE, e, false);
//				System.exit(1);
//			}
//
//			try {
//				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
//				Object ob = in.readObject();
//				if (ob instanceof String[][]) {
//					String[][] stuff = (String[][]) ob;
//					String key = stuff[0][0];
//					String process = stuff[0][1];
//					Sheet keys = instance.getStorage().getKeysSheet();
//					Row row = keys.getRow(keys.getFirstRowNum());
//					boolean hasKey = false;
//					for (Cell cell : row) {
//						if (cell.getCellTypeEnum().equals(CellType.STRING)) {
//							String cellValue = cell.getStringCellValue();
//							if (cellValue.equals(key)) {
//								hasKey = true;
//							}
//						}
//					}
//					if (hasKey) {
//						String[][] values = Arrays.copyOfRange(stuff, 1, stuff.length);
//						if (process.equals(instance.getProperties().getProperty("userSyncProcess"))) {
//							instance.getLogger().log(
//									"User sync process activated from ip " + socket.getRemoteSocketAddress(), false);
//							StringBuilder sb = new StringBuilder();
//							for(String[] value: values){
//								sb.append("\n");
//								for(String string: value){
//									sb.append(string + '\n');
//								}
//							}
//							instance.getLogger().log("User Sync " + sb.toString(), false);
//							sync.syncUsers(key, values);
//						} else if (process.equals(instance.getProperties().getProperty("roleSyncProcess"))) {
//							instance.getLogger().log(
//									"Role sync process activated from ip " + socket.getRemoteSocketAddress(), false);
//							String[] roles = stuff[1];
//							sync.syncRoles(key, roles);
//						} else {
//							instance.getLogger().log(
//									"Recived an unknow process value from the ip " + socket.getRemoteSocketAddress(),
//									Level.WARNING, false);
//						}
//					} else {
//						instance.getLogger().log("Recived invalid key from the ip " + socket.getRemoteSocketAddress(),
//								Level.WARNING, false);
//					}
//				} else {
//					instance.getLogger().log(
//							"Object recived that wasn't a string list from ip " + socket.getRemoteSocketAddress(),
//							Level.WARNING, false);
//				}
//				socket.close();
//				in.close();
//			} catch (ClassNotFoundException e) {
//				instance.getLogger().log("Recived a class not found exception on socket", Level.SEVERE, e, false);
//			} catch (IOException e) {
//				instance.getLogger().log("Recived a IO exception on socket", Level.SEVERE, e, false);
//			}
//
//		}
//	}
//
//}
