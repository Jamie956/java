package com.jamie.concurrency.juc.collections;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueTest {

    public static void putElement(ArrayBlockingQueue qu) {
        try {
            qu.put(1);
            qu.put(2);
            qu.put(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void takeElement(ArrayBlockingQueue qu) {
        try {
            qu.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //put blocking
    private static void test1() {
        ArrayBlockingQueue qu = new ArrayBlockingQueue(2);
        new Thread(() -> putElement(qu)).start();
        new Thread(() -> takeElement(qu)).start();
    }

    //take blocking
    private static void test2() {
        ArrayBlockingQueue qu = new ArrayBlockingQueue(2);
        new Thread(() -> takeElement(qu)).start();
        new Thread(() -> putElement(qu)).start();
    }

    public static void main(String[] args) {
        test1();
    }
}
