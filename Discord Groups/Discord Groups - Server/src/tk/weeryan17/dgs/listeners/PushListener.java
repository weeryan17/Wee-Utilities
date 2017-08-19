package tk.weeryan17.dgs.listeners;

import com.arsenarsen.githubwebhooks4j.events.EventListener;
import com.arsenarsen.githubwebhooks4j.events.PushEvent;
import com.arsenarsen.githubwebhooks4j.objects.Commit;

import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.util.EmbedBuilder;
import tk.weeryan17.dgs.DiscordGroups;

public class PushListener implements EventListener<PushEvent> {
	DiscordGroups instance;

	public PushListener(DiscordGroups instance) {
		this.instance = instance;
	}

	@Override
	public void handle(PushEvent event) {
		instance.getLogger().log("Push event recived", true);
		EmbedBuilder embed = new EmbedBuilder();
		embed.withAuthorName(event.getSender().getLogin());
		embed.withAuthorIcon(event.getSender().getAvatarUrl());
		embed.withAuthorUrl(event.getSender().getProfile());
		embed.withDesc(event.getSender().getLogin()
				+ " commited to [Wee-Utilities](https://github.com/weeryan17/Wee-Utilities)\n");
		for (Commit commit : event.getCommits()) {
			embed.appendField("Commit:",
					"[" + commit.getId().substring(0, 7) + "](" + commit.getUrl() + ")\n Branch `"
							+ event.getRef().substring(event.getRef().lastIndexOf('/') + 1) + "` " + "```"
							+ commit.getMessage() + "```",
					false);

			if (commit.getAdded().length > 0) {
				StringBuilder sb = new StringBuilder();
				sb.append("```Markdown\n");
				int i = 0;
				for (String filePath : commit.getAdded()) {
					if (i++ <= 5) {
						String file = filePath.substring(filePath.lastIndexOf("/") + 1);
						sb.append("* " + file + "\n\n");
					}
				}
				if (commit.getAdded().length > 5) {
					sb.append("...");
				}
				String added = sb.toString() + "```";
				embed.appendField("Added files", added, false);
			}

			if (commit.getRemoved().length > 0) {
				StringBuilder sb = new StringBuilder();
				sb.append("```Markdown\n");
				int i = 0;
				for (String filePath : commit.getRemoved()) {
					if (i++ <= 5) {
						String file = filePath.substring(filePath.lastIndexOf("/") + 1);
						sb.append("* " + file + "\n\n");
					}
				}
				if (commit.getRemoved().length > 5) {
					sb.append("...");
				}
				String removed = sb.toString() + "```";
				embed.appendField("Removed files", removed, false);
			}

			if (commit.getModified().length > 0) {
				StringBuilder sb = new StringBuilder();
				sb.append("```Markdown\n");
				int i = 0;
				for (String filePath : commit.getModified()) {
					if (i++ <= 5) {
						String file = filePath.substring(filePath.lastIndexOf("/") + 1);
						sb.append("* " + file + "\n\n");
					}
				}
				if (commit.getModified().length > 5) {
					sb.append("...");
				}
				String modified = sb.toString() + "```";
				embed.appendField("Modified files", modified, false);
			}

		}
		IRole role = null;
		int pos = 0;
		for (IRole rawRole : instance.client.getOurUser().getRolesForGuild(instance.getMainGuild())) {
			int rawPos = rawRole.getPosition();
			if (rawPos > pos) {
				role = rawRole;
				pos = rawPos;
			}
		}
		embed.withColor(role.getColor());

		instance.getMainGuild().getChannelByID(281978233468092416L).sendMessage(embed.build());
	}

}
