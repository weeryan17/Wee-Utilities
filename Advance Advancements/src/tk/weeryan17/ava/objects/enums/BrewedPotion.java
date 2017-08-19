package tk.weeryan17.ava.objects.enums;

public enum BrewedPotion {
	//TODO fill this in
	STRONG_SWIFTNESS ("minecraft:strong_swiftness");
	
	private final String s;
	
	BrewedPotion(String s) {
		this.s = s;
	}
	
	public boolean equalsName(String s){
		return this.s.equals(s);
	}
	
	public String toString(){
		return s;
	}
}
