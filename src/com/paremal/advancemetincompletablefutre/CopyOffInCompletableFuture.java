package com.paremal.advancemetincompletablefutre;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CopyOffInCompletableFuture {
    public static void main(String[] args)
            throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        CompletableFuture<Integer> completableFuture = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        System.out.println("intFuture1 is Sleeping in thread: "
                                + Thread.currentThread().getName());
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 5;
                }, executorService);

        CompletableFuture<Integer> copy = completableFuture.copy();

        System.out.println(copy.isDone());
        System.out.println(copy.isDone());

        Thread.sleep(3100);
        executorService.shutdown();
        executorService.awaitTermination(4, TimeUnit.SECONDS);
    }
}


