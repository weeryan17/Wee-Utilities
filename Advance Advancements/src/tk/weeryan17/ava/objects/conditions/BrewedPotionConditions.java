package tk.weeryan17.ava.objects.conditions;

import tk.weeryan17.ava.objects.Conditions;
import tk.weeryan17.ava.objects.enums.BrewedPotion;

public class BrewedPotionConditions extends Conditions {
	private BrewedPotion potion;

	public BrewedPotion getPotion() {
		return potion;
	}

	public void setPotion(BrewedPotion potion) {
		this.potion = potion;
	}
}
