package com.weeryan17.dgs.util.versoning;

import java.util.HashMap;

public class PluginVersion {
	static HashMap<String, PluginVersion> versions = new HashMap<String, PluginVersion>();
	
	String version;
	String title;
	String[] description;
	String download;
	
	public PluginVersion(String version, String title, String[] description, String download){
		this.version = version;
		this.title = title;
		this.description = description;
		this.download = download;
		
		versions.put(version, this);
	}
	
	public static PluginVersion getPluginVersion(String version){
		return versions.get(version);
	}
	
	public String getVersion(){
		return version;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String[] getDescription(){
		return description;
	}
	
	public String getDownload(){
		return download;
	}
}
