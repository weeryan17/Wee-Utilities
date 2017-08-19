package tk.weeryan17.ava.objects.enums;

public enum ItemType {
	POTION ("");
	
	private final String s;
	
	ItemType(String s) {
		this.s = s;
	}
	
	public boolean equals(String s) {
		return this.s.equals(s);
	}
	
	public String toString() {
		return s;
	}
}
