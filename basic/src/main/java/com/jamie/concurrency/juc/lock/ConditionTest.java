package com.jamie.concurrency.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    public static void work(ReentrantLock lock, Condition condition) {
        lock.lock();
        try {
            try {
                System.out.println("Begin");
                condition.await();
                System.out.println("End");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
    }

    public static void continueWork(ReentrantLock lock, Condition condition) {
        lock.lock();
        try {
            System.out.println("Signal All");
//            condition.signalAll();
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> work(lock, condition)).start();

        TimeUnit.SECONDS.sleep(3);
        continueWork(lock, condition);
    }
}