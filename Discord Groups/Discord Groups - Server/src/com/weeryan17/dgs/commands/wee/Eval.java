package com.weeryan17.dgs.commands.wee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.weeryan17.dgs.DiscordGroups;
import com.weeryan17.dgs.commands.DiscordGroupsCommandBase;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

public class Eval implements DiscordGroupsCommandBase {
	DiscordGroups instance;
	ArrayList<String> ids;
	
	List<String> imports = Arrays.asList("com.weeryan17.dgs.*",
			"com.weeryan17.dgs.commands.*",
			"com.weeryan17.dgs.listeners.*",
			"com.weeryan17.dgs.listeners.discord.*",
			"com.weeryan17.dgs.socket.*",
			"com.weeryan17.dgs.util.*",
			"java.util.streams.*",
            "java.util.*",
            "java.text.*",
            "java.math.*",
            "java.time.*",
            "java.io.*");
	
	ScriptEngine engine;
	public Eval(DiscordGroups instance){
		this.instance = instance;
		ids = new ArrayList<String>();
		ids.add("215644829969809421");
		ScriptEngineManager scriptMan = new ScriptEngineManager();
		engine = scriptMan.getEngineByName("java");
	}
	@Override
	public void onCommand(String[] args, IChannel channel, IUser sender) {
		if(ids.contains(sender.getID())){
			String code = Arrays.stream(args).collect(Collectors.joining(" "));
			String imports = this.imports.stream().map(s -> "import " + s + ';').collect(Collectors.joining("\n"));
			String result = "";
			try {
				result = String.valueOf(engine.eval(imports + code));
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(result.length() >= 2000){
				EmbedBuilder builder = new EmbedBuilder();
				builder.appendField("Code", code, true);
				builder.appendField("Result", "`Ouput too long. Check output file`", true);
			} else {
				EmbedBuilder builder = new EmbedBuilder();
				builder.appendField("Code", "`" + code + "`", true);
				builder.appendField("Result", "`" + result + "`", true);
			}
		}
	}
	
}
