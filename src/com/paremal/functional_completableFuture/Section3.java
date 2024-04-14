package com.paremal.functional_completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Section3 {
    public static void main(String[] args)
            throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture<Integer> integerFuture1 = CompletableFuture
                .supplyAsync(() ->{
                    System.out.println(Thread.currentThread().getName());
                    return 5;
                }, executorService);
        integerFuture1.thenRunAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            String successMessage =
                    "I am doing something else once" +
                            " that future has been triggered!";
            System.out.println
                    (successMessage);
            System.out.println("Run inside of " +
                    Thread.currentThread().getName());
        });
        Thread.sleep(100);
        executorService.shutdown();
        executorService.awaitTermination(4, TimeUnit.SECONDS);
    }
}
