package com.example.juc;

import java.util.concurrent.*;

public class FutureTest {
    public static void main(String[] args) {
        try {
            FutureTask<Integer> ft = new FutureTask<Integer>(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    System.out.println(Thread.currentThread().getName() + " execute call()");
                    return 2;
                }
            });

            new Thread(ft).start();
            System.out.println("ret：" + ft.get());

//            ExecutorService pool = Executors.newSingleThreadExecutor();
//            pool.submit(ft);
//            System.out.println("ret：" + ft.get());
//            pool.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}