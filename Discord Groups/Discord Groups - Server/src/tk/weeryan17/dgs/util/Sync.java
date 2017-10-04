//package tk.weeryan17.dgs.util;
//
//import java.awt.Color;
//import java.util.Arrays;
//import java.util.List;
//
//import sx.blah.discord.handle.obj.IGuild;
//import sx.blah.discord.handle.obj.IRole;
//import sx.blah.discord.handle.obj.IUser;
//import tk.weeryan17.dgs.DiscordGroups;
//
//public class Sync {
//	DiscordGroups instance;
//
//	public Sync(DiscordGroups instance) {
//		this.instance = instance;
//	}
//
//	/**
//	 * Sync users and their roles. syncRoles should probably be called first.
//	 * 
//	 * @param key
//	 *            The key that was sent to the socket.
//	 * @param users
//	 *            The user and their roles in a double list.
//	 */
//	public void syncUsers(String key, String[][] users) {
//		Long guildId = instance.getStorage().getGuildIdFromKey(key);
//		IGuild guild = instance.client.getGuildByID(guildId);
//		for (String[] userR : users) {
//			Long UUID = instance.getStorage().getUserIDFromSpigot(userR[0]);
//			IUser user = guild.getUserByID(UUID);
//			String[] roles = Arrays.copyOfRange(userR, 1, userR.length - 1);
//			List<IRole> listRoles = user.getRolesForGuild(guild);
//			for (String roleName : roles) {
//				for (IRole role : guild.getRolesByName(roleName)) {
//					listRoles.add(role);
//				}
//			}
//			guild.editUserRoles(user, listRoles.toArray(new IRole[0]));
//		}
//	}
//
//	public void syncRoles(String key, String[] roles) {
//		Long guildId = instance.getStorage().getGuildIdFromKey(key);
//		IGuild guild = instance.client.getGuildByID(guildId);
//		for (String role : roles) {
//			if (guild.getRolesByName(role).size() == 0) {
//				IRole iRole = guild.createRole();
//				iRole.changeName(role);
//				iRole.changeColor(Color.CYAN);
//			}
//		}
//	}
//}
