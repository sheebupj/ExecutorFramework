package com.paremal.CompletableFutre;

import java.util.concurrent.*;

public class Section5 {
    public static void main(String[] args)
            throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture<Integer> integerCompletableFuture =
                CompletableFuture.supplyAsync(() -> {
                    System.out.println(Thread.currentThread().getName());
                    return 4;
                }, executorService);
        integerCompletableFuture.thenAccept(System.out::println);

        //The following is going to use the default Thread Pool

        CompletableFuture<Integer> integerCompletableFuture2 =
                CompletableFuture.supplyAsync(() -> {
                    System.out.println(Thread.currentThread().getName());
                    return 4;
                });
        integerCompletableFuture2.thenAccept(System.out::println);

        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);
    }
}
