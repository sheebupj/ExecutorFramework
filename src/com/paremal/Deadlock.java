package com.paremal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class Deadlock {

    public static void main(String[] args) {

        Object lock1=new Object();
        Object lock2=new Object();

        Runnable runnable1=()->{
            synchronized (lock1){
                System.out.println("1acquired lock1");
                synchronized (lock2){
                    System.out.println("1acquired lock2");

                }
                System.out.println("1relelased lock2");
            }
            System.out.println("1relelased lock1");
        };
        Runnable runnable2=()->{
            synchronized (lock2){
                System.out.println("2acquired lock2");
                synchronized (lock1){
                    System.out.println("2acquired lock1");

                }
                System.out.println("2relelased lock1");
            }
            System.out.println("2relelased lock2");
        };
        ExecutorService executorService= Executors.newFixedThreadPool(2);
       // executorService.submit(runnable1);
      //  executorService.submit(runnable2);
       String name="sheebu";
       String name1=new String("sheebu");
       String name3=new String("sheebu");
       String name2="sheebu";
       System.out.println(name);
       String str= """
                      Sheebu PJ,
                      Paremal House,
                      PO velimukku,
                      Malappuram District,
                      676317.""";
        System.out.println(str);
        int a=5,b=7;
       // String str1= STR."/{a}+/{b}+\{a+b}";





    }


}
class Abc{
    public String str;
}
