package tk.weeryan17.ava.objects.enums;

public enum EnchantmentType {
	PROTECTION ("");
	
	private final String s;
	
	EnchantmentType(String s) {
		this.s = s;
	}
	
	public boolean equals(String s) {
		return this.s.equals(s);
	}
	
	public String toString() {
		return s;
	}
}
