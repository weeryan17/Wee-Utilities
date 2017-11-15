package tk.weeryan17.dgs.commands.developer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;
import tk.weeryan17.dgs.DiscordGroups;
import tk.weeryan17.dgs.commands.DiscordGroupsCommandBase;

/**
 * Part of this I borrowed from Flarebot https://github.com/FlareBot/FlareBot
 * 
 * @author weeryan17
 *
 */
public class EvalCommand implements DiscordGroupsCommandBase {
	DiscordGroups instance;
	ArrayList<Long> ids;

	List<String> imports = Arrays.asList("tk.weeryan17.dgs.*", "tk.weeryan17.dgs.tkmands.*",
			"tk.weeryan17.dgs.listeners.*", "tk.weeryan17.dgs.listeners.discord.*", "tk.weeryan17.dgs.socket.*",
			"tk.weeryan17.dgs.util.*", "tk.weeryan17.dgs.util.storage.*", "sx.blah.discord.util.*", "org.apache.poi.xssf.usermodel.*",
			"org.apache.poi.ss.usermodel.*", "org.apache.poi.hssf.usermodel.*", "java.util.streams.*", "java.util.*",
			"java.text.*", "java.math.*", "java.time.*", "java.io.*");

	ScriptEngine engine;

	public EvalCommand(DiscordGroups instance) {
		this.instance = instance;
		ids = instance.getDevelopersIds();
		ScriptEngineManager scriptMan = new ScriptEngineManager();
		engine = scriptMan.getEngineByName("groovy");
	}

	@Override
	public void onCommand(String[] args, IChannel channel, IUser sender) {
		instance.getLogger().log("Discord user with the id of " + sender.getLongID() + " tried to use the eval command",
				true);
		if (ids.contains(sender.getLongID())) {
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
			} catch (Exception e) {
				instance.getLogger().log("The script encountered an exception on eval", Level.WARNING, e, true);
				result = e.getMessage() + '\n';
				for (StackTraceElement element : e.getStackTrace()) {
					result = result + element.getClassName() + " class generated an error on line "
							+ element.getLineNumber() + " in the method " + element.getMethodName() + "()." + "\n";
				}
			}
			if (result.length() >= 1024) {
				EmbedBuilder builder = new EmbedBuilder();
				builder.withTitle("Evaluation");
				builder.appendField("Code", "```" + code + "```", false);
				builder.appendField("Result", "```Ouput too long. Check output file```", false);

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
				instance.getLogger().logResult(result);
				channel.sendMessage(builder.build());
			} else {
				EmbedBuilder builder = new EmbedBuilder();
				builder.withTitle("Evaluation");
				builder.appendField("Code", "```" + code + "```", false);
				builder.appendField("Result", "```" + result + "```", false);
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
				channel.sendMessage(builder.build());
			}
			channel.setTypingStatus(false);
		}
	}

}
