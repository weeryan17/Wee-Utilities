package tk.weeryan17.ava.objects.enums;

public enum Dimension {
	NETHER ("the_nether"),
	END ("the_end"),
	OVERWORLD ("overworld");
	
	private final String s;
	
	Dimension(String s) {
		this.s = s;
	}
	
	public boolean equalsName(String s){
		return this.s.equals(s);
	}
	
	public String toString(){
		return s;
	}
}
