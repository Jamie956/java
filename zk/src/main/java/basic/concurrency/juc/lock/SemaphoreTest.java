package basic.concurrency.juc.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {
    static Semaphore semaphore = new Semaphore(3);

    static void task(int sec) {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " start");
            TimeUnit.SECONDS.sleep(sec);
            System.out.println(Thread.currentThread().getName() + " end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> task(2)).start();
        new Thread(() -> task(2)).start();
        new Thread(() -> task(6)).start();

        new Thread(() -> task(2)).start();
        new Thread(() -> task(2)).start();
    }

}

