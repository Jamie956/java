package com.example;

import java.util.concurrent.TimeUnit;

public class TimeUnitTest {
    public static void main(String[] args) throws InterruptedException {
        //sleep 13 min
//        TimeUnit.MINUTES.sleep(13);

        //sleep 1 hours
//        TimeUnit.HOURS.sleep(1);

        TimeUnitTest test = new TimeUnitTest();
        Thread thread = new Thread(() -> test.work());

        //join after 10s
        TimeUnit.SECONDS.timedJoin(thread, 10);
    }

    public synchronized void work() {
        System.out.println("Begin Work");
        try {
            TimeUnit.SECONDS.timedWait(this, 30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Work End");
    }

}
