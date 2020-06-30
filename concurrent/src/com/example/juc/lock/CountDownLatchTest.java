package com.example.juc.lock;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    static void work(CountDownLatch lock) {
        System.out.println(Thread.currentThread().getName() + " ok");
        lock.countDown();
    }

    public static void main(String[] args) {
        CountDownLatch lock = new CountDownLatch(3);

        new Thread(() -> work(lock)).start();
        new Thread(() -> work(lock)).start();
        new Thread(() -> work(lock)).start();

        try {
            lock.await();
            System.out.println("1");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}