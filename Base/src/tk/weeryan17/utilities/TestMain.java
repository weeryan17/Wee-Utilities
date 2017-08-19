package tk.weeryan17.utilities;

import tk.weeryan17.ava.objects.conditions.sub.Distance;
import tk.weeryan17.ava.objects.conditions.sub.DistanceCondition;

public class TestMain {

	public static void main(String[] args) {
		
		DistanceCondition dist = new DistanceCondition();
		Distance absol = new Distance();
		absol.setMax(10);
		absol.setMin(0);
		dist.setAbsolute(absol);
		System.out.println(Base.GSON.toJson(dist));
	}

}
