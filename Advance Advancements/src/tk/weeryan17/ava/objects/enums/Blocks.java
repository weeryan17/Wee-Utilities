package tk.weeryan17.ava.objects.enums;

public enum Blocks {
	AIR ("minecraft:air");
	
	private final String s;
	
	Blocks(String s) {
		this.s = s;
	}
	
	public boolean equalsName(String s){
		return this.s.equals(s);
	}
	
	public String toString(){
		return s;
	}
}
