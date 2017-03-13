package com.weeryan17.dgs.util.timers;

import java.util.TimerTask;
import static spark.Spark.*;

public class WebUpdater extends TimerTask {

	@Override
	public void run() {
		port(6000);
		get("/", (req, res) -> {
			return "stuffs";
		});
	}

}
