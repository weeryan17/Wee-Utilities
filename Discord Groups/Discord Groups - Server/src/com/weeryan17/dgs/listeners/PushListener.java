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
		EmbedBuilder embed =  new EmbedBuilder();
		ShortUser author = event.getCommits()[0].getAuthor(); //Assuming commit 0 contains the only commiter
		String authorName = author.getUsername();
		embed.withAuthorName(authorName);
		embed.withDesc(authorName + " commited to [Wee-Utilities](https://github.com/weeryan17/Wee-Utilities)\n");
		for(Commit commit : event.getCommits()){
			embed.appendField("Commit:","[" + commit.getId().substring(0, 7) + "](" + commit.getUrl() + ")\n Branch `" + event.getRef().substring(event.getRef().lastIndexOf('/') + 1) + "`" + "```" + commit.getMessage() + "```", false);
        	
        	if(commit.getAdded().length > 0){
                StringBuilder sb = new StringBuilder();
                sb.append("```Markdown\n");
				for(String file: commit.getAdded()){
					sb.append("* " + file + "\n\n");
				}
				String added = sb.toString() + "```";
				embed.appendField("Added files", added, false);
			}
        	
        	if(commit.getRemoved().length > 0){
                StringBuilder sb = new StringBuilder();
                sb.append("```Markdown\n");
				for(String file: commit.getRemoved()){
					sb.append("* " + file + "\n\n");
				}
				String removed = sb.toString() + "```";
				embed.appendField("Removed files", removed, false);
			}
        	
        	if(commit.getModified().length > 0){
                StringBuilder sb = new StringBuilder();
                sb.append("```Markdown\n");
				for(String file: commit.getModified()){
					sb.append("* " + file + "\n\n");
				}
				String modified = sb.toString() + "```";
				embed.appendField("Modified files", modified, false);
			}
			
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

