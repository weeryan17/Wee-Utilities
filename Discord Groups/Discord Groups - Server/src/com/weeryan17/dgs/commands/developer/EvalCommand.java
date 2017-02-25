package com.weeryan17.dgs.commands.developer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.weeryan17.dgs.DiscordGroups;
import com.weeryan17.dgs.commands.DiscordGroupsCommandBase;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

/**
 * Part of this I borrowed from Flarebot
 * https://github.com/FlareBot/FlareBot
 * 
 * @author weeryan17
 *
 */
public class EvalCommand implements DiscordGroupsCommandBase {
	DiscordGroups instance;
	ArrayList<String> ids;
	
	List<String> imports = Arrays.asList("com.weeryan17.dgs.*",
			"com.weeryan17.dgs.commands.*",
			"com.weeryan17.dgs.listeners.*",
			"com.weeryan17.dgs.listeners.discord.*",
			"com.weeryan17.dgs.socket.*",
			"com.weeryan17.dgs.util.*",
			"sx.blah.discord.util.*",
			"org.apache.poi.xssf.usermodel.*",
			"org.apache.poi.ss.usermodel.*",
			"org.apache.poi.hssf.usermodel.*",
			"java.util.streams.*",
            "java.util.*",
            "java.text.*",
            "java.math.*",
            "java.time.*",
            "java.io.*");
	
	ScriptEngine engine;
	public EvalCommand(DiscordGroups instance){
		this.instance = instance;
		ids = new ArrayList<String>();
		ids.add("215644829969809421");
		ScriptEngineManager scriptMan = new ScriptEngineManager();
		engine = scriptMan.getEngineByName("groovy");
	}
	
	@Override
	public void onCommand(String[] args, IChannel channel, IUser sender) {
		instance.getLogger().log("Discord user with the id of " + sender.getID() + " tried to use the eval command", true);
		if(ids.contains(sender.getID())){
			channel.setTypingStatus(true);
			String code = Arrays.stream(args).collect(Collectors.joining(" "));
			String imports = this.imports.stream().map(s -> "import " + s + ';').collect(Collectors.joining("\n"));
			engine.put("instance", instance);
			engine.put("channel", channel);
			engine.put("guild", channel.getGuild());
			engine.put("sender", sender);
			String result = "";
			try {
				result = String.valueOf(engine.eval(imports + code));
			} catch (ScriptException e) {
				instance.getLogger().log("The script encountered an exception on eval", Level.WARNING, e, true);
				for(StackTraceElement element: e.getStackTrace()){
					result = result + element.getClassName() + " class generated an error on line " + element.getLineNumber() + " in the method " + element.getMethodName() + "()." + "\n";
				}
			}
			if(result.length() >= 1024){
				EmbedBuilder builder = new EmbedBuilder();
				builder.withTitle("Evaluation");
				builder.appendField("Code", "```" + code + "```", false);
				builder.appendField("Result", "```Ouput too long. Check output file```", false);
				
				if(!channel.isPrivate()){
					IRole role = null;
					int pos = 0;
					for(IRole rawRole : instance.client.getOurUser().getRolesForGuild(channel.getGuild())){
						int rawPos = rawRole.getPosition();
						if(rawPos > pos){
							role = rawRole;
							pos = rawPos;
						}
					}
					builder.withColor(role.getColor());
				}
				instance.getStorage().logResult(result);
				channel.sendMessage(builder.build());
			} else {
				EmbedBuilder builder = new EmbedBuilder();
				builder.withTitle("Evaluation");
				builder.appendField("Code", "```" + code + "```", false);
				builder.appendField("Result", "```" + result + "```", false);
				if(!channel.isPrivate()){
					IRole role = null;
					int pos = 0;
					for(IRole rawRole : instance.client.getOurUser().getRolesForGuild(channel.getGuild())){
						int rawPos = rawRole.getPosition();
						if(rawPos > pos){
							role = rawRole;
							pos = rawPos;
						}
					}
					builder.withColor(role.getColor());
				}
				channel.sendMessage(builder.build());
			}
			channel.setTypingStatus(false);
		}
	}
	
}
