package com.example.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {
    static CountDownLatch latch = new CountDownLatch(4);

    static void work(int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);

            System.out.println(Thread.currentThread().getName() + " Working...");
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("start");
        new Thread(() -> work(3)).start();
        new Thread(() -> work(3)).start();
        new Thread(() -> work(3)).start();
        new Thread(() -> work(5)).start();

        latch.await();//count==0
        System.out.println("end");
    }
}