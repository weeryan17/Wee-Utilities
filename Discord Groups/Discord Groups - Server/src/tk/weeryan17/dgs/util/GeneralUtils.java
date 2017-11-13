package tk.weeryan17.dgs.util;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class GeneralUtils {
	public static ArrayList<String> jsonArray(JsonArray array) {
		ArrayList<String> arrayOut = new ArrayList<>();
		for(JsonElement object : array) {
			arrayOut.add(object.getAsString());
		}
		return arrayOut;
	}
}
