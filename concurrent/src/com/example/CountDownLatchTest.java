package com.example;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    static CountDownLatch latch = new CountDownLatch(3);

    static void work() {
        System.out.println(Thread.currentThread().getName() + " Work End");
        latch.countDown();
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> work()).start();
        new Thread(() -> work()).start();
        new Thread(() -> work()).start();
//        new Thread(() -> work()).start();

        latch.await();//当前线程（主线程）等待计数器值为0，才会执行
        System.out.println("Main Thread Work End");

        /**
         Thread-0 Work End
         Thread-1 Work End
         Thread-2 Work End
         Main Thread Work End
         */
    }
}