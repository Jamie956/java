package com.example;

import java.util.concurrent.TimeUnit;

public class TimeUnitTest {
    public static void main(String[] args) throws InterruptedException {
        //sleep 3s
        TimeUnit.SECONDS.sleep(3);

        TimeUnitTest test = new TimeUnitTest();
        Thread thread = new Thread(() -> test.work());

        //join after 10s
        TimeUnit.SECONDS.timedJoin(thread, 10);

        thread.start();
    }

    public synchronized void work() {
        System.out.println("Begin Work");
        try {
            TimeUnit.SECONDS.timedWait(this, 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Work End");
    }

}
