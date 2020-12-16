package com.jamie.concurrency.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

    public static void task(Lock lock) {
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        } finally {
            lock.unlock();
        }
    }

    //read lock test
    public static void test1() {
        Lock lock = new ReentrantReadWriteLock().readLock();

        new Thread(() -> task(lock)).start();
        new Thread(() -> task(lock)).start();
        new Thread(() -> task(lock)).start();
    }

    //write lock test
    public static void test2() {
        Lock lock = new ReentrantReadWriteLock().writeLock();

        new Thread(() -> task(lock)).start();
        new Thread(() -> task(lock)).start();
        new Thread(() -> task(lock)).start();
    }

    public static void main(String[] args) {
        test1();
    }
}