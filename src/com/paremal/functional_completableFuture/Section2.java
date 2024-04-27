package com.paremal.functional_completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Section2 {
    public static void main(String[] args)
            throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture<Integer> integerFuture1 = CompletableFuture
                .supplyAsync(() -> 5, executorService);
        integerFuture1.thenApplyAsync(x1 -> {
            System.out.println("In Block 1:" + Thread.currentThread().getName());
            return "" + (x1 + 19);
        },executorService).thenAcceptAsync((x) -> {
            System.out.println("Accepting in 2:" + Thread.currentThread().getName());
            System.out.println("x = " + x);
        }, executorService);
        System.out.println("Main 3:" + Thread.currentThread().getName());
        Thread.sleep(100);
        executorService.shutdown();
        executorService.awaitTermination(4, TimeUnit.SECONDS);
    }
}
