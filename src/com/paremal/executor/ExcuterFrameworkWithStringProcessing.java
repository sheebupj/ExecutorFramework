package com.paremal.executor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Map;

public class ExcuterFrameworkWithStringProcessing {
	/*
	 * String processing in Excecutor framework using callable and future and Map sorting usingKey
	 */
	public static void main(String[] args) {
		String[] words1={"Hi Hello Welcome to Infosys Hi Welcome Again Hi &&&&&&&&&&",
				"A Callable interface defined in java.util.concurrent package &&&&&&&&&&",
				"An object of Callable returns a computed result done by a thread in contrast to a Runnable interface that can only run the thread &&&&&&&&&&",
				"The Callable object returns Future object that provides methods to monitor the progress of a task executed by a thread &&&&&&&&&&",
				"An object of the Future used to check the status of a Callable interface and retrieves the result from Callable once the thread has done &&&&&&&&&&"};
		List<Future<Map<String,Integer>>> lftr= new ArrayList<>();
		ExecutorService executor= Executors.newSingleThreadExecutor();
		for(String w:words1) {
			Callable<Map<String,Integer>> cl=()-> Arrays.stream(w.split(" ")).collect(Collectors.toMap(Function.identity(),v->1,Integer::sum));
			lftr.add(executor.submit(cl));
		}	
		lftr.stream().forEach(m-> {
			try {
				m.get().entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::println);
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		ExecutorService executor1= Executors.newSingleThreadExecutor();
		Runnable runnable=()->System.out.println("hello world");
		executor1.submit(runnable);
		
	}

}
