package com.paremal.CompletableFutre;
import java.util.concurrent.*;
public class Section3 {

        public static void main(String[] args)
                throws InterruptedException {
            ExecutorService executorService = Executors.newFixedThreadPool(4);
            CompletableFuture<Integer> integerCompletableFuture =
                    CompletableFuture.completedFuture(10);

            CompletableFuture<Integer> handle =
                    integerCompletableFuture.handle((i, e) -> {
                        if (i != null) {
                            return i + 10;
                        } else {
                            return 0;
                        }
                    });

            handle.thenAccept(System.out::println);


            executorService.shutdown();
            executorService.awaitTermination(4, TimeUnit.SECONDS);
        }
    }

