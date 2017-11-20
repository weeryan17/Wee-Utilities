package tk.weeryan17.dgs.util;

import java.util.ArrayList;
import java.util.Random;

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
	
	public String getRandomString(int length) {
		Random random = new Random();
		ArrayList<String> chars = getRandomCharArray();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < length; i++) {
			int ran = random.nextInt(chars.size());
			sb.append(chars.get(ran));
		}
		return sb.toString();
	}
	
	private static ArrayList<String> getRandomCharArray() {
		ArrayList<String> chars = new ArrayList<>();
		for (int i = 0; i <= 9; i++) {
			chars.add(String.valueOf(i));
		}
		chars.add("q");
		chars.add("w");
		chars.add("e");
		chars.add("r");
		chars.add("t");
		chars.add("y");
		chars.add("u");
		chars.add("i");
		chars.add("o");
		chars.add("p");
		chars.add("-");
		chars.add("a");
		chars.add("s");
		chars.add("d");
		chars.add("f");
		chars.add("g");
		chars.add("h");
		chars.add("j");
		chars.add("k");
		chars.add("l");
		chars.add(".");
		chars.add("z");
		chars.add("x");
		chars.add("c");
		chars.add("v");
		chars.add("b");
		chars.add("n");
		chars.add("m");
		return chars;
	}
}
