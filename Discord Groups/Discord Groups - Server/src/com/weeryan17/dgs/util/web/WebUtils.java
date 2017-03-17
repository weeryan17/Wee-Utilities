package com.weeryan17.dgs.util.web;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebUtils {
	public static final ExecutorService websiteExecutor =
			Executors.newCachedThreadPool(r -> new Thread(() -> {
				r.run();
			}));
	public void sendToWebsite(){
		websiteExecutor.submit(() -> {
			
		});
	}
}
