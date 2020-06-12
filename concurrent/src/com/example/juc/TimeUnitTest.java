package com.example.juc;

import java.util.concurrent.TimeUnit;

public class TimeUnitTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("sleep begin");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("sleep end");

        TimeUnitTest test = new TimeUnitTest();
        new Thread(() -> test.work()).start();

    }

    public synchronized void work() {
        System.out.println("Begin Work");
        try {
            TimeUnit.SECONDS.timedWait(this, 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Work End");
    }

}
