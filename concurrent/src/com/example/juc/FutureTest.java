package com.example.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTest {
    static class work implements Callable {
        @Override
        public Object call() throws Exception {
            System.out.println(Thread.currentThread().getName() + " Started By Callable");
            return 2;
        }
    }

    public static void main(String[] args) {
        try {
            FutureTask<Integer> ft = new FutureTask<Integer>(new work());
            new Thread(ft).start();
            System.out.println("ret：" + ft.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}