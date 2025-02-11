package com.paremal.producer_consumer;

import java.util.Random;
import java.util.concurrent.*;

public class Producer_consumer_blockingQueue {

    public static void main(String[] args) {
        BlockingQueue<Integer> integerBlockingQueue= new ArrayBlockingQueue<>(10);
        Consumer consumer= new Consumer(integerBlockingQueue);
        Producer producer=new Producer(integerBlockingQueue);
        ExecutorService executorService= Executors.newFixedThreadPool(2);
        executorService.submit(producer);
        executorService.submit(consumer);
        System.out.println(producer.blockingQueue.size());
    }
}
class Consumer implements Runnable{
    BlockingQueue<Integer> blockingQueue;
    int taken;

    public Consumer(BlockingQueue blockingDeque) {
        this.blockingQueue = blockingDeque;
    }

    @Override
    public void run() {
        System.out.println("consumer");
        while (true) {
            try {
                taken = blockingQueue.take();
                System.out.println("Consumed :" + taken+ "size:"+blockingQueue.size());

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
class Producer implements Runnable{
    BlockingQueue<Integer> blockingQueue;
    Random random= new Random();

    public Producer(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        System.out.println("producer");
        while (true){
            blockingQueue.add(random.nextInt(1,100));
            System.out.println("Produced :" + blockingQueue.peek()+ "size:"+blockingQueue.size());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
