package com.weeryan17.dgs.listeners;

import com.arsenarsen.githubwebhooks4j.events.EventListener;
import com.arsenarsen.githubwebhooks4j.events.PushEvent;
import com.arsenarsen.githubwebhooks4j.objects.Commit;
import com.arsenarsen.githubwebhooks4j.objects.ShortUser;
import com.weeryan17.dgs.DiscordGroups;

import sx.blah.discord.util.EmbedBuilder;

public class WebhooksListener implements EventListener<PushEvent> {
	DiscordGroups instance;
	public WebhooksListener(DiscordGroups instance){
		this.instance = instance;
	}
	
	@Override
	public void handle(PushEvent event) {
		for(Commit commit : event.getCommits()){
			EmbedBuilder embed =  new EmbedBuilder();
			
			ShortUser author = commit.getAuthor();
			String authorName = author.getUsername();
			
			embed.withAuthorName(authorName);
			
			ShortUser commiter = commit.getCommiter();
			String commiterName = commiter.getUsername();
			
			embed.withDesc(authorName + " commit with " + commiterName + " to [Wee-Utilities](https://github.com/weeryan17/Wee-Utilities)");
			
			embed.appendDesc("Commit " + commit.getId() + ": " + commit.getMessage());
			
			for(String file: commit.getAdded()){
				embed.appendField("Added files", file, false);
			}
			
			for(String file: commit.getRemoved()){
				embed.appendField("Removed files", file, false);
			}
			
			for(String file: commit.getModified()){
				embed.appendField("Modified files", file, false);
			}
			
			instance.getMainGuild().getChannelByID("280176956958441472").sendMessage(embed.build());
		}
	}
	
}
