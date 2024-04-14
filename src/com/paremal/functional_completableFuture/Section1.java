package com.paremal.functional_completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Section1 {
    public static void main(String[] args) throws InterruptedException {


        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture<Integer> integerFuture1 = CompletableFuture
                .supplyAsync(() -> 31, executorService);

        integerFuture1.thenApply(x -> String.valueOf(x + 19)).thenAccept(System.out::println);

        Thread.sleep(100);
        executorService.shutdown();
        executorService.awaitTermination(4, TimeUnit.SECONDS);
    }
}
