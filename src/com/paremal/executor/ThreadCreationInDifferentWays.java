package com.paremal.executor;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ThreadCreationInDifferentWays {

    /*
     * Thread creation using Executor framework, Runnable, Callable, Future,
     * extending threads etc
     */

    public static void main(String[] args) {
        String[] words1 = {"Hi Hello Welcome to Infosys Hi Welcome Again Hi &&&&&&&&&&",
                "A Callable interface defined in java.util.concurrent package &&&&&&&&&&",
                "An object of Callable returns a computed result done by a thread in contrast to a Runnable interface that can only run the thread &&&&&&&&&&",
                "The Callable object returns Future object that provides methods to monitor the progress of a task executed by a thread &&&&&&&&&&",
                "An object of the Future used to check the status of a Callable interface and retrieves the result from Callable once the thread has done &&&&&&&&&&"};

        /*
         * String processing in Excecutor framework using callable and future and Map
         * sorting usingKey
         */
        List<Future<Map<String, Integer>>> lftr = new ArrayList<>();
        ExecutorService executor = Executors.newCachedThreadPool();
        for (String w : words1) {
            Callable<Map<String, Integer>> cl = () -> Arrays.stream(w.split(" "))
                    .collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum));
            lftr.add(executor.submit(cl));
        }
        lftr.stream().forEach(m -> {
            try {
                m.get().entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::println);
            } catch (InterruptedException | ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        /*
         * Thread creation using executor and runnable
         */
        ExecutorService executor1 = Executors.newSingleThreadExecutor();
        Runnable runnable = () -> System.out.println("########################hello world");
        executor1.submit(runnable);

        /*
         * Thread creation using Thread class and runnable
         */
        Runnable rnbl = () -> System.out.println("hello world1");
        Thread t1 = new Thread(rnbl);
        t1.start();

        /*
         * Thread creation using Thread class and runnable
         */
        Runnable thread1 = () -> System.out.println("hello world2");
        Executors.newCachedThreadPool().submit(thread1);

        Executors.newSingleThreadExecutor().submit(() -> System.out.println("Hello World 2.1"));

        /*
         * Thread creation extending thread class
         */
        Thread t2 = new Thread() {
            public void run() {
                System.out.println("hello world3");

            }
        };
        t2.start();
        System.out.println("Hello from   " + Thread.currentThread().getName());
        new Thread(() -> System.out.println("Hello World from " + Thread.currentThread().getName())).start();
        /*
         * Reading the data.txt file and split each lines to words and build a words
         * list
         */
        List<String> words = null;
        try {
            words = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())
                    .flatMap(line -> Arrays.stream(line.split(" "))).collect(Collectors.toList());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ThreadCreationInDifferentWays creationInDifferentWays = new ThreadCreationInDifferentWays();

        /*
         * CompletableFuture.runAsync
         */
        //creationInDifferentWays.processFileRunAsync(words);

        /*
         * CompletableFuture .supplyAsync method call
         */
        CompletableFuture<Map<String, Integer>> cfsa = creationInDifferentWays.processFileSupplyAsync(words);
        while (!cfsa.isDone()) {
            System.out.println("working");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        try {
            System.out.println(cfsa.get().getClass().descriptorString());
            Map<String, Integer> map = (Map<String, Integer>) cfsa.get();
            map.entrySet().stream().forEach(System.out::println);
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * runAsync
     *
     * @param words
     */
    void processFileRunAsync(List<String> words) {
        System.out.println(
                "process words++++++++++++++++++++++++++++++runAsync++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        CompletableFuture
                .runAsync(() -> words.stream().collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum))
                        .entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::println));
    }

    /**
     * supplyAsync
     *
     * @param words
     * @return
     */
    CompletableFuture<Map<String, Integer>> processFileSupplyAsync(List<String> words) {
        System.out.println("process words++++++++++++++runSupplyAsync++++++++++++++++++");
        ExecutorService executorService= Executors.newCachedThreadPool();
        CompletableFuture<Map<String, Integer>> cf = CompletableFuture
                .supplyAsync(() -> words.stream().collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum)),executorService);

        return cf.thenApply((m) -> m.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new)));


    }

}
