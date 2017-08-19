package tk.weeryan17.ava.objects.enums;

/**
 * Represents the different types of entitys that advancements can handle.
 * 
 * @author weeryan17
 *
 */
public enum EntityType {
	//TODO fill this.
	DROOPED_ITEM ("item");
	
	private final String s;
	
	EntityType(String s) {
		this.s = s;
	}
	
	public boolean equalsName(String s){
		return this.s.equals(s);
	}
	
	public String toString(){
		return s;
	}
}
