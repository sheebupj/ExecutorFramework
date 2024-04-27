package com.paremal.advancemetincompletablefutre;

import java.util.concurrent.*;

public class TimeoutCompletableFuture {
    public static void main(String[] args)
            throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture<Integer> completableFuture =
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 30;
                });

        completableFuture
                .orTimeout(4, TimeUnit.SECONDS)
                .exceptionally(t -> -1)
                .thenAccept(System.out::println);

        Thread.sleep(4100);
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);
    }
}


