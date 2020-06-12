package com.example.juc;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    static CountDownLatch latch = new CountDownLatch(4);

    static void work() {
        try {
            Thread.sleep(3000);

            System.out.println(Thread.currentThread().getName() + " Work");
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static void work2() {
        try {
            Thread.sleep(5000);

            System.out.println(Thread.currentThread().getName() + " Work");
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println("start");
        new Thread(() -> work()).start();
        new Thread(() -> work()).start();
        new Thread(() -> work()).start();
        new Thread(() -> work2()).start();

        latch.await();//count==0
        System.out.println("end");

    }
}