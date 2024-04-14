package com.paremal.CompletableFutre;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyCompletableFuture {
    public static void main(String[] args) {

        ExecutorService executorService=Executors.newCachedThreadPool();
        CompletableFuture<String> stringCompletableFuture=CompletableFuture.supplyAsync(()->{
            System.out.println("Sleeping in a thread");
            try {
                Thread.sleep(3000);
                return "String";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        },executorService);
        stringCompletableFuture.thenAccept(System.out::println);

    }
}
