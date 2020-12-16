package com.jamie.concurrency.juc.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    public static void task(CyclicBarrier barrier) {
        try {
            System.out.println(Thread.currentThread().getName() + " before wait");
            barrier.await();
            System.out.println(Thread.currentThread().getName() + " after wait");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("barrier action");
            }
        });

        new Thread(() -> task(barrier)).start();
        new Thread(() -> task(barrier)).start();
        new Thread(() -> task(barrier)).start();

//        new Thread(() -> task(barrier)).start();
//        new Thread(() -> task(barrier)).start();
//        new Thread(() -> task(barrier)).start();

    }
}