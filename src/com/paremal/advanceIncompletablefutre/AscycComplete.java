package com.paremal.advanceIncompletablefutre;

import java.util.concurrent.*;

public class AscycComplete {


    public static void main(String[] args)
            throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        CompletableFuture<Integer> completableFuture =
                new CompletableFuture<>();
        completableFuture.thenAccept(System.out::println);
        Thread.sleep(1000);

        completableFuture.completeAsync(() -> 100, executorService);

        Thread.sleep(1100);
        executorService.shutdown();
        executorService.awaitTermination(4, TimeUnit.SECONDS);
    }


}


