package com.paremal.advanceIncompletablefutre;
import java.util.concurrent.*;
public class DelayedExecutor {
        public static void main(String[] args)
                throws InterruptedException {
            ExecutorService executorService = Executors.newFixedThreadPool(4);
            Executor executor = CompletableFuture.delayedExecutor(10, TimeUnit.SECONDS);
            CompletableFuture.supplyAsync(() -> 4, executor).thenAccept(System.out::println);
            Thread.sleep(10100);
            executorService.shutdown();
            executorService.awaitTermination(4, TimeUnit.SECONDS);
        }
    }


