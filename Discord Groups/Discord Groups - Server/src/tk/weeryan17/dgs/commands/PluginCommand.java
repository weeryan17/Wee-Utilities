package tk.weeryan17.dgs.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;
import tk.weeryan17.dgs.DiscordGroups;
import tk.weeryan17.dgs.util.versoning.PluginVersion;

public class PluginCommand implements DiscordGroupsCommandBase {
	DiscordGroups instance;

	public PluginCommand(DiscordGroups instance) {
		this.instance = instance;
	}

	@Override
	public void onCommand(String[] args, IChannel channel, IUser sender) {
		if (args.length == 0) {
			EmbedBuilder eb = instance.getMessageUtil().getBaseEmbed(sender, channel);
			eb.appendDesc("This Menu give info on the plugin side of things as well as download links.");

			eb.appendField("Latest stable version",
					"[" + PluginVersion.getLatest().getVersion() + "](" + PluginVersion.getLatest().getDownload() + ")",
					true);
			eb.appendField("Latest", "[" + PluginVersion.getHighest().getVersion() + "]("
					+ PluginVersion.getHighest().getDownload() + ")", true);

			String[] header = new String[] { "Version", "Title", "Description", "Download link" };

			List<List<String>> table = new ArrayList<>();

			List<String> fotter = null;
			if (PluginVersion.getAllVersions().size() > 10) {
				int pages = (PluginVersion.getAllVersions().size()/10) + 1;
				fotter = new ArrayList<String>();
				fotter.add("Page");
				fotter.add("1/" + pages);
				fotter.add("");
				fotter.add("");
			}

			for (PluginVersion version : PluginVersion.getAllVersions()) {
				ArrayList<String> versionArray = new ArrayList<String>();
				versionArray.add(version.getVersion());
				versionArray.add(version.getTitle());
				StringBuilder sb = new StringBuilder();
				for (String descLine : version.getDescription()) {
					sb.append(descLine + "\n");
				}
				versionArray.add(sb.toString());
				versionArray.add(version.getDownload());
			}
			instance.getMessageUtil().makeAsciiTable(Arrays.asList(header), table, fotter);
		}
	}
}
