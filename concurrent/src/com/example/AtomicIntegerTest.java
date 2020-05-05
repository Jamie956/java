package com.example;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
    public static AtomicInteger sharedValue = new AtomicInteger();

    public static void increment() {
        for (int i = 0; i < 10; i++) {
            sharedValue.incrementAndGet();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int maxThreads = 10000;
        for (int i = 0; i < maxThreads; i++) {
            Thread thread = new Thread(() -> increment());
            thread.start();
        }
        Thread.sleep(3000);
        System.out.println(sharedValue.get() == 10000 * 10);
    }
}