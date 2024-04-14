package com.paremal.functional_completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Section5 {
    public static void main(String[] args)
            throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture<String> stringFuture1 = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        System.out.println("stringFuture1 is sleeping in thread: "
                                + Thread.currentThread().getName());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "Bloomington, MN";
                }, executorService);

        stringFuture1.thenApply(Integer::parseInt).handle(
                (item, throwable) -> {
                    if (throwable == null) return item;
                    else return -1;
                }).thenAccept(System.out::println);

        System.out.println("This message should appear first.");
        Thread.sleep(1100);
        executorService.shutdown();
        executorService.awaitTermination(4, TimeUnit.SECONDS);
    }
}
