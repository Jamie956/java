package com.example.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest {

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private String content = "Old";

    public void write() {
        lock.writeLock().lock();
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
            lock.writeLock().unlock();
        }
    }

    public void read() {
        lock.readLock().lock();
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
            lock.readLock().unlock();
        }
    }

    public static void main(String[] args) {
        final ReentrantReadWriteLockTest test = new ReentrantReadWriteLockTest();
        new Thread(() -> test.write()).start();
        new Thread(() -> test.write()).start();
        new Thread(() -> test.read()).start();
        new Thread(() -> test.read()).start();

    }
}