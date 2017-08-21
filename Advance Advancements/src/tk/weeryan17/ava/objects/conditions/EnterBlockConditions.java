package tk.weeryan17.ava.objects.conditions;

import tk.weeryan17.ava.objects.Conditions;
import tk.weeryan17.ava.objects.enums.Blocks;

public class EnterBlockConditions extends Conditions {
	private Blocks block;
	
	//TODO state.

	public Blocks getBlock() {
		return block;
	}

	public void setBlock(Blocks block) {
		this.block = block;
	}
}
