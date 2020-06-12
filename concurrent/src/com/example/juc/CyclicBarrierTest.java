package com.example.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    static CyclicBarrier barrier = new CyclicBarrier(3, () -> System.out.println("condition call back"));

    static void go() {
        System.out.println(Thread.currentThread().getName() + " ready");
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " breakout");
    }

    public static void main(String[] args) {

        new Thread(() -> go()).start();
        new Thread(() -> go()).start();
        new Thread(() -> go()).start();
    }
}