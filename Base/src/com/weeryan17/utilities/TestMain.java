package com.weeryan17.utilities;

import tk.weeryan17.com.ava.objects.conditions.sub.DistanceCondition;

public class TestMain {

	public static void main(String[] args) {
		DistanceCondition dist = new DistanceCondition();
		DistanceCondition.Distance absol = dist.new Distance();
		absol.setMax(10);
		absol.setMin(0);
		dist.setAbsolute(absol);
		System.out.println(Base.GSON.toJson(dist));
	}

}
