package com.paremal.executor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo {
    public static void main(String[] args)
            throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        System.out.println("Sleeping in thread: "
                                + Thread.currentThread().getName());
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 5;
                }, executorService);

        integerCompletableFuture.thenAccept(System.out::println);

        executorService.shutdown();
        executorService.awaitTermination(4, TimeUnit.SECONDS);
    }
}
