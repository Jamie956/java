package com.example.juc.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    public static void work(ReentrantLock lock) {
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        } finally {
            lock.unlock();
        }
    }

    public static void test1() {
        ReentrantLock lock = new ReentrantLock();

        new Thread(() -> work(lock)).start();
        new Thread(() -> work(lock)).start();
        new Thread(() -> work(lock)).start();
    }

    //可重入锁实验，state>1
    public static void test2() {
        ReentrantLock lock = new ReentrantLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();//state==1
                    try {
                        lock.lock();//state==2
                        try {
                            lock.lock();//state==3
                        } finally {
                            lock.unlock();
                        }
                    } finally {
                        lock.unlock();
                    }
                } finally {
                    lock.unlock();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        test2();
    }
}
