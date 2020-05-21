package com.paremal.executor;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleThreadPool {
	
	public static void main(String[] args) {
		ExecutorService executorService= Executors.newFixedThreadPool(8);
		
		for(int i=1;i<=8;i++) {
			Runnable runnable= new Runner(0.0, 9+Math.random(), "Athlet"+i);
			executorService.execute(runnable);
		}
		executorService.shutdown();
		
	}
	
	

}
