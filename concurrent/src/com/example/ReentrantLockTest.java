package com.example;

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
                Thread.sleep(100);
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
                Thread.sleep(100);
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

        /**
         without lock：
         Thread[Thread-1,5,main] Read content is: Old
         Thread[Thread-2,5,main] Read content is: Old
         Thread[Thread-0,5,main] Write content to: New

         using lock：
         Thread[Thread-0,5,main] LOCK
         Thread[Thread-0,5,main] Write content to: New
         Thread[Thread-0,5,main] UNLOCK
         Thread[Thread-1,5,main] LOCK
         Thread[Thread-1,5,main] Read content is: New
         Thread[Thread-1,5,main] UNLOCK
         Thread[Thread-2,5,main] LOCK
         Thread[Thread-2,5,main] Read content is: New
         Thread[Thread-2,5,main] UNLOCK
         **/
    }
}
