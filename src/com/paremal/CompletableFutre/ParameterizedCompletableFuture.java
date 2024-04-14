package com.paremal.CompletableFutre;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParameterizedCompletableFuture {
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public static CompletableFuture<Integer> getTemperatureInFahrenheit(final String cityState) {
        return CompletableFuture.supplyAsync(() -> {
            //We go into a webservice to find the weather...
            System.out.println("In getTemperatureInFahrenheit: " +
                    Thread.currentThread().getName());
            System.out.println("Finding the temperature for " + cityState);
            return 75;
        }, executorService);
    }

    public static void main(String[] args)
            throws InterruptedException {

        CompletableFuture<Integer> temperatureInSacramentoFuture =
                getTemperatureInFahrenheit("Sacramento, CA");

        temperatureInSacramentoFuture.thenAccept(System.out::println);

        executorService.shutdown();
        executorService.awaitTermination(4, TimeUnit.SECONDS);
    }
}
