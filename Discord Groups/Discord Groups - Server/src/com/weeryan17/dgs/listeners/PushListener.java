package com.weeryan17.dgs.listeners;

import com.arsenarsen.githubwebhooks4j.events.EventListener;
import com.arsenarsen.githubwebhooks4j.events.PushEvent;
import com.arsenarsen.githubwebhooks4j.objects.Commit;
import com.arsenarsen.githubwebhooks4j.objects.ShortUser;
import com.weeryan17.dgs.DiscordGroups;

import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.util.EmbedBuilder;

public class PushListener implements EventListener<PushEvent> {
	DiscordGroups instance;
	public PushListener(DiscordGroups instance){
		this.instance = instance;
	}
	
	@Override
	public void handle(PushEvent event) {
		instance.getLogger().log("Push event recived", true);
		for(Commit commit : event.getCommits()){
			EmbedBuilder embed =  new EmbedBuilder();
			
			ShortUser author = commit.getAuthor();
			String authorName = author.getUsername();
			
			embed.withAuthorName(authorName);
			
			embed.withDesc(authorName + " commited to [Wee-Utilities](https://github.com/weeryan17/Wee-Utilities)         ");
			
			embed.appendDesc("Commit " + commit.getId() + ":                           " + commit.getMessage());
			
			if(commit.getAdded().length > 0){
				String added = "";
				for(String file: commit.getAdded()){
					added = added + "* " + file + "\n";
				}
				embed.appendField("Added files", added, false);
			}
			
			if(commit.getRemoved().length > 0){
				String removed = "";
				for(String file: commit.getRemoved()){
					removed = removed + "* " + file + "\n";
				}
				embed.appendField("Removed files", removed, false);
			}
			
			if(commit.getModified().length > 0){
				String modified = "";
				for(String file: commit.getModified()){
					modified = modified + "* " + file + "\n";
				}
				embed.appendField("Modified files", modified, false);
			}
			
			IRole role = null;
			int pos = 0;
			for(IRole rawRole : instance.client.getOurUser().getRolesForGuild(instance.getMainGuild())){
				int rawPos = rawRole.getPosition();
				if(rawPos > pos){
					role = rawRole;
					pos = rawPos;
				}
			}
			embed.withColor(role.getColor());
			
			instance.getMainGuild().getChannelByID("281978233468092416").sendMessage(embed.build());
		}
	}
	
}
