package com.paremal.CompletableFutre;

import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class CompletableFutureDemo {
    public record Customer(String name, String age, String city, Double netWorth) {
    }

    public record Employee(String firstName, String lastName, Double salary, String department, List<String> phoneNo) {
    }
    public static void main(String[] args) throws InterruptedException {
        final List<String> phones = Arrays.asList(new String[] { "9387690660", "9188584218" });

        List<Customer> customers = List.of(new Customer("zohn", "15", "NewJersy", 1000.0),
                new Customer("aby", "15", "NewYork", 2000.0), new Customer("john3", "15", "NewJersy", 4000.0),
                new Customer("john4", "15", "NewJersy", 5000.0));

        List<Employee> empList = List.of(new Employee("Jason", "Red", 5000.0, "IT", phones),
                new Employee("Ashly", "Green", 7601.0, "IT", phones),
                new Employee("Mathew", "Indigo", 3587.5, "Sales", phones),
                new Employee("James", "Indigo", 7600.0, "Marketing", phones),
                new Employee("Luke", "Indigo", 8200.0, "IT", phones),
                new Employee("Jason", "Blue", 6200.0, "Sales", phones),
                new Employee("Jason", "Blue", 3200.0, "finance", phones),
                new Employee("Wendy", "Brown", 4236.4, "Marketing", phones),
                new Employee("Wendy", "Brown", 6200.0, "Marketing", phones));
        System.out.println("starting");

        CompletableFuture<List<Employee>> completableFuture= CompletableFuture.supplyAsync(()->{
            return empList.stream().sorted(Comparator.comparing(Employee::salary)).collect(Collectors.toList());
        });
        completableFuture.thenApply(e->{
                    e.forEach(System.out::println);
            System.out.println("...............................................................");
                    return e;
        });

        CompletableFuture<Map<String,Employee>> mapCompletableFuture=completableFuture.thenApply(elist->
                elist.stream()
                        .collect(Collectors.groupingBy(Employee::department,
                                Collectors.collectingAndThen(Collectors.maxBy
                                        (Comparator.comparing(Employee::salary)), Optional::get))));
        Thread.sleep(1000);

        mapCompletableFuture.thenAccept(e-> e.entrySet().stream().forEach(System.out::println));




        System.out.println("end");

    }
}
