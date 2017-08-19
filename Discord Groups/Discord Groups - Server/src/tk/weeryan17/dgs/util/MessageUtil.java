package tk.weeryan17.dgs.util;

import java.util.ArrayList;
import java.util.Collections;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;
import tk.weeryan17.dgs.DiscordGroups;

public class MessageUtil {

	DiscordGroups instance;

	public MessageUtil(DiscordGroups instance) {
		this.instance = instance;
	}

	public EmbedBuilder getBaseEmbed(IUser sender, IChannel channel) {
		EmbedBuilder builder = new EmbedBuilder();
		builder.withAuthorName("@Discord Groups#2320");
		builder.withAuthorUrl("https://github.com/weeryan17/Wee-Utilities/tree/master/Discord%20Groups");
		builder.withThumbnail("https://www.dropbox.com/s/ly3s749g2om8o7x/discordgroupsicon.png?dl=1");
		builder.withFooterText("Command requested by @" + sender.getName() + "#" + sender.getDiscriminator());
		if (!channel.isPrivate()) {
			IRole role = null;
			int pos = 0;
			for (IRole rawRole : instance.client.getOurUser().getRolesForGuild(channel.getGuild())) {
				int rawPos = rawRole.getPosition();
				if (rawPos > pos) {
					role = rawRole;
					pos = rawPos;
				}
			}
			builder.withColor(role.getColor());
		}
		return builder;
	}

	public Object getUserFromString(String string, IChannel channel) {
		if (string.substring(0, 2).equals("<@!") && string.substring(string.length() - 1).equals(">")) {
			String stringId = string.substring(2, string.length() - 1);
			try {
				Long id = Long.parseLong(stringId);
				return channel.getGuild().getUserByID(id);
			} catch (Exception e) {
				return "invalid";
			}
		} else {
			try {
				Long id = Long.parseLong(string);
				return channel.getGuild().getUserByID(id);
			} catch (Exception e) {
				ArrayList<IUser> users = (ArrayList<IUser>) channel.getGuild().getUsersByName(string, true);
				if (users.size() >= 2) {
					return "multiple";
				} else {
					try {
						return users.get(0);
					} catch (Exception e1) {
						return "invalid";
					}
				}
			}
		}
	}

	public Object getRoleFromString(String string, IChannel channel) {
		if (string.substring(0, 2).equals("<@&") && string.substring(string.length() - 1).equals(">")) {
			String stringId = string.substring(2, string.length() - 1);
			try {
				Long id = Long.parseLong(stringId);
				return channel.getGuild().getRoleByID(id);
			} catch (Exception e) {
				return "invalid";
			}
		} else {
			try {
				Long id = Long.parseLong(string);
				return channel.getGuild().getRoleByID(id);
			} catch (Exception e) {
				ArrayList<IRole> users = (ArrayList<IRole>) channel.getGuild().getRolesByName(string);
				if (users.size() >= 2) {
					return "multiple";
				} else {
					try {
						return users.get(0);
					} catch (Exception e1) {
						return "invalid";
					}
				}
			}
		}
	}
	
	public String makeAsciiTable(java.util.List<String> headers, java.util.List<java.util.List<String>> table, java.util.List<String> footer) {
        StringBuilder sb = new StringBuilder();
        int padding = 1;
        int[] widths = new int[headers.size()];
        for (int i = 0; i < widths.length; i++) {
            widths[i] = 0;
        }
        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).length() > widths[i]) {
                widths[i] = headers.get(i).length();
                if (footer != null) {
                    widths[i] = Math.max(widths[i], footer.get(i).length());
                }
            }
        }
        for (java.util.List<String> row : table) {
            for (int i = 0; i < row.size(); i++) {
                String cell = row.get(i);
                if (cell.length() > widths[i]) {
                    widths[i] = cell.length();
                }
            }
        }
        sb.append("```").append("\n");
        String formatLine = "|";
        for (int width : widths) {
            formatLine += " %-" + width + "s |";
        }
        formatLine += "\n";
        sb.append(appendSeparatorLine("+", "+", "+", padding, widths));
        sb.append(String.format(formatLine, headers.toArray()));
        sb.append(appendSeparatorLine("+", "+", "+", padding, widths));
        for (java.util.List<String> row : table) {
            sb.append(String.format(formatLine, row.toArray()));
        }
        if (footer != null) {
            sb.append(appendSeparatorLine("+", "+", "+", padding, widths));
            sb.append(String.format(formatLine, footer.toArray()));
        }
        sb.append(appendSeparatorLine("+", "+", "+", padding, widths));
        sb.append("```");
        return sb.toString();
	}
	
	private String appendSeparatorLine(String left, String middle, String right, int padding, int... sizes) {
        boolean first = true;
        StringBuilder ret = new StringBuilder();
        for (int size : sizes) {
            if (first) {
                first = false;
                ret.append(left).append(String.join("", Collections.nCopies(size + padding * 2, "-")));
            } else {
                ret.append(middle).append(String.join("", Collections.nCopies(size + padding * 2, "-")));
            }
        }
        return ret.append(right).append("\n").toString();
    }
}
