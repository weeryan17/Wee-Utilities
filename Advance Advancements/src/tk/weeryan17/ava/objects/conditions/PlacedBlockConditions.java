package tk.weeryan17.ava.objects.conditions;

import tk.weeryan17.ava.objects.Conditions;
import tk.weeryan17.ava.objects.conditions.sub.ItemCondition;
import tk.weeryan17.ava.objects.enums.Blocks;

public class PlacedBlockConditions extends Conditions {
	private Blocks block;
	
	private ItemCondition item;

	//TODO state

	public Blocks getBlock() {
		return block;
	}

	public void setBlock(Blocks block) {
		this.block = block;
	}

	public ItemCondition getItem() {
		return item;
	}

	public void setItem(ItemCondition item) {
		this.item = item;
	}
}
