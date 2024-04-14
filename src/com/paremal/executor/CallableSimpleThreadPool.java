package com.paremal.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableSimpleThreadPool {
	
	public static void main(String[] args) {
		ExecutorService executorService= Executors.newFixedThreadPool(8);
		List<Future<String>> future= new ArrayList<Future<String>>();
		
		for(int i=1;i<=8;i++) {
			Callable<String> runnable= new CallableRunner(0.0, 9+Math.random(), "Athlet"+i);
			Future<String> ftr=executorService.submit(runnable);
			future.add(ftr);
		}
		for(Future<String> ftr:future) {
			try {
				System.out.println(ftr.get());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		executorService.shutdown();
		Thread thread= new Thread(()-> System.out.println("hello world"));
		thread.start();
		
	}
	
	

}
