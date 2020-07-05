package basic.concurrency.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

    public static void work(Thread thread) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " 3");
        LockSupport.unpark(thread);
    }

    public static void main(String[] args) {

        Thread main = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " 1");
        new Thread(() -> work(main)).start();

        System.out.println(Thread.currentThread().getName() + " 2");
        LockSupport.park(main);

        System.out.println(Thread.currentThread().getName() + " 4");
    }

}