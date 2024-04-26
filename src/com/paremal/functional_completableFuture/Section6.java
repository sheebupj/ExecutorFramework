package com.paremal.functional_completableFuture;
import java.util.StringJoiner;
import java.util.concurrent.*;
public class Section6 {
    public static class Coordinates {
        public double latitude;
        public double longitude;

        public Coordinates(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ",
                    Coordinates.class.getSimpleName() + "[", "]")
                    .add("latitude=" + latitude)
                    .add("longitude=" + longitude)
                    .toString();
        }
    }

    public static CompletableFuture<String>
    findTheClosestCityState(final Coordinates coordinates) {
        return CompletableFuture.supplyAsync(() -> {
            //Call Webservice to find the closest city and state
            System.out.println("Finding the closest city and state with coordinates for " + coordinates);
            return "Bloomington, MN";
        });
    }

    public static CompletableFuture<Integer>
    getTemperatureInFahrenheit(final String cityState) {
        return CompletableFuture.supplyAsync(() -> {
            //Call Webservice to find the temperature for city and state
            System.out.println("Finding the temperature for " + cityState);
            return 75;
        });
    }


    public static void main(String[] args)
            throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture<String> theClosestCityState =
                findTheClosestCityState(new Coordinates(44.8408, 93.2983));
        CompletableFuture<Integer> integerCompletableFuture =
                theClosestCityState.thenCompose(Section6::getTemperatureInFahrenheit);
        integerCompletableFuture.thenAccept(System.out::println);
        Thread.sleep(100);
        executorService.shutdown();
        executorService.awaitTermination(4, TimeUnit.SECONDS);
    }
}
