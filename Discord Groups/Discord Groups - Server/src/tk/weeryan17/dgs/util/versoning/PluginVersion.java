package tk.weeryan17.dgs.util.versoning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class PluginVersion {
	static HashMap<String, PluginVersion> versions = new HashMap<String, PluginVersion>();
	
	String version;
	String title;
	String[] description;
	String download;
	
	static String latest;
	static String highest;
	
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
	
	public static List<PluginVersion> getAllVersions(){
		ArrayList<PluginVersion> vers = new ArrayList<>();
		Iterator<Entry<String, PluginVersion>> versionIt = versions.entrySet().iterator();
		while(versionIt.hasNext()){
			Map.Entry<String, PluginVersion> entry = versionIt.next();
			PluginVersion version = entry.getValue();
			vers.add(version);
			versionIt.remove();
		}
		return vers;
	}
	
	public static void setLatest(String latest){
		PluginVersion.latest = latest;
	}
	
	public static void setHighest(String highest){
		PluginVersion.highest = highest;
	}
	
	public static PluginVersion getLatest(){
		return versions.get(latest);
	}
	
	public static PluginVersion getHighest(){
		return versions.get(highest);
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
