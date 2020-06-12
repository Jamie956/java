package com.example.juc;

import java.util.concurrent.*;

public class ExecutorTest {
    public static void main(String[] args) {
        //using pool execute
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future future = es.submit(new CallableTask());

        //using thread execute
//        FutureTask<Integer> future = new FutureTask<Integer>(new CallableTask());
//        new Thread(future).start();

        try {
            System.out.println("ret：" + future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        es.shutdown();
    }
    static class CallableTask implements Callable {
        @Override
        public Object call() throws Exception {
            System.out.println(Thread.currentThread().getName() + " Started By Callable");
            return 2;
        }
    }
}