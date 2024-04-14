package com.paremal.CompletableFutre;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Section4 {
    public static void main(String[] args)
            throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture<Integer> failedFuture =
                CompletableFuture.failedFuture(new Throwable("Unable to complete"));

        CompletableFuture<Integer> handle =
                failedFuture.handle((i, e) -> {
                    if (i != null) {
                        return i + 10;
                    } else {
                        return 0;
                    }
                });

        handle.thenAccept(System.out::println);
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);
    }
}
