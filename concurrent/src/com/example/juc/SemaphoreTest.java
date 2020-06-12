package com.example.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {
    static Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) {
        Work work = new Work();
        new Thread(() -> work.task()).start();
        new Thread(() -> work.task()).start();
        new Thread(() -> work.task()).start();

        new Thread(() -> work.task()).start();
        new Thread(() -> work.task()).start();
    }

    static class Work {
        public void task() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " start");
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }
}

