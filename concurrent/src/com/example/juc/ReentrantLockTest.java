package com.example.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    private final Lock lock = new ReentrantLock();
    private String content = "Old";

    public void write() {
        lock.lock();
        System.out.println(Thread.currentThread() + " LOCK");
        try {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
             }
            content = "New";
            System.out.println(Thread.currentThread() + " Write content to: " + content);
        } finally {
            System.out.println(Thread.currentThread() + " UNLOCK");
            lock.unlock();
        }
    }

    public void read() {
        lock.lock();
        System.out.println(Thread.currentThread() + " LOCK");
        try {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + " Read content is: " + content);
        } finally {
            System.out.println(Thread.currentThread() + " UNLOCK");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReentrantLockTest test = new ReentrantLockTest();
        new Thread(() -> test.write()).start();
        new Thread(() -> test.read()).start();
        new Thread(() -> test.read()).start();

    }
}
