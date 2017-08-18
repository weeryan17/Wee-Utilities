package com.weeryan17.utilities;

import com.weeryan17.utilities.api.advancement.objects.conditions.sub.DistanceCondition;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DistanceCondition dist = new DistanceCondition();
		DistanceCondition.Distance absol = dist.new Distance();
		absol.setMax(10);
		absol.setMin(0);
		dist.setAbsolute(absol);
		System.out.println(Base.GSON.toJson(dist));
	}

}
