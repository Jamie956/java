package com.example;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {
    public static void main(String[] args) {
        WC wc = new WC();
        new Thread(() -> wc.use()).start();
        new Thread(() -> wc.use()).start();
        new Thread(() -> wc.use()).start();

        new Thread(() -> wc.use()).start();
        new Thread(() -> wc.use()).start();
    }

    static class WC {
        private Semaphore semaphore = new Semaphore(3);

        public void use() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " in using");
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + " ok");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }
}

