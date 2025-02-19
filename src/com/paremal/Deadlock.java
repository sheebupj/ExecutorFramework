package com.paremal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Deadlock {

    public static void main(String[] args) {

        Object lock1=new Object();
        Object lock2=new Object();

        Runnable runnable1=()->{
            synchronized (lock1){
                System.out.println("acquired lock1");
                synchronized (lock2){
                    System.out.println("acquired lock2");

                }
                System.out.println("relelased lock2");
            }
            System.out.println("relelased lock1");
        };
        Runnable runnable2=()->{
            synchronized (lock1){
                System.out.println("acquired lock1");
                synchronized (lock2){
                    System.out.println("acquired lock2");

                }
                System.out.println("relelased lock2");
            }
            System.out.println("relelased lock1");
        };
        ExecutorService executorService= Executors.newFixedThreadPool(2);
        executorService.submit(runnable1);
        executorService.submit(runnable2);

    }

}
