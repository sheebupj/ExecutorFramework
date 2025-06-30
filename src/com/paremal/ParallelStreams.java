package com.paremal;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParallelStreams {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
       /* List.of(1,2,3,4,5)
                .stream()
                .map(w -> doWork(w))
                .forEach(s -> System.out.print(s + " "));*/

        System.out.println();
        var timeTaken = (System.currentTimeMillis() - start) / 1000;
        System.out.println("Time: " + timeTaken + " seconds");

        long start1 = System.currentTimeMillis();
        List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
                .parallelStream()
                .map(w -> doWork(w))
                .forEach(s -> System.out.print(s + " "));

        System.out.println();
        var timeTaken1 = (System.currentTimeMillis() - start1) / 1000;
        System.out.println("Time: " + timeTaken1 + " seconds");
        System.out.print(List.of(1, 2, 3, 4, 5, 6)
                .parallelStream()
                .findAny().get());

        List<Integer> ints = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0).toList();

        List<int[]> intPairs = ints.stream()
                .flatMap(i -> ints.stream()
                        .filter(j -> i + j == 4)
                        .map(j -> new int[]{i, j}))
                .toList();
        intPairs.forEach(a -> System.out.println(a[0] + "," + a[1] + "  "));

        System.out.println(List.of('w', 'o', 'l', 'f')
                .parallelStream()
                .reduce("",
                        (s1, c) -> s1 + c,
                        (s2, s3) -> s2 + s3));
        Stream<String> stream = Stream.of("s","h", "e", "e", "b","u", "p", "a", "r","e", "m", "a", "l","j","a","y","a","d","e","v","a","n").parallel();
        SortedSet<String> set = stream.collect(ConcurrentSkipListSet::new,
                Set::add,
                Set::addAll);
        System.out.println("###"+set);

        Stream<String> stream1 = Stream.of("s","h", "e", "e", "b","u", "p", "a", "r","e", "m", "a", "l","j","a","y","a","d","e","v","a","n");
        SortedSet<String> set1 = stream1.collect(TreeSet::new,
                Set::add,
                Set::addAll);
        System.out.println("###1"+set1);

        Stream<String> ohMy = Stream.of("lions", "tigers", "bears").parallel();
        ConcurrentMap<Integer, String> map = ohMy
                .collect(Collectors.toConcurrentMap(String::length,
                        k -> k,
                        (s1, s2) -> s1 + "," + s2));
        System.out.println(map); // {5=lions,bears, 6=tigers}
        System.out.println(map.getClass());

        var ohMy1 = Stream.of("lions", "tigers", "bears").parallel();
        ConcurrentMap<Integer, List<String>> map1 = ohMy1.collect(
                Collectors.groupingByConcurrent(String::length));
        System.out.println(map); // {5=[lions, bears], 6=[tigers]}




        List<Integer> intStrm = List.of(9, 3, 1, 8, 4, 6, 5, 2, 0, 7);

        Random random = new Random();
        IntStream.iterate(1, i -> i <= 20, i -> i + 1).boxed()
                .map(i -> random.nextInt(5))
                .collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new))
                .entrySet().stream().limit(2).forEach(kv-> System.out.println(kv.getKey()+":"+kv.getValue()));

        int k = 5;


    }


    private static int doWork(int input) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {}
        return input;
    }
}
