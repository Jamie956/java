package basic.concurrency;

import java.util.concurrent.TimeUnit;

public class JoinTest {
    static void work() {
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("start");
        Thread t = new Thread(() -> work());
        t.start();

        t.join();//waits for thread t to die
//        t.join(1000);
        System.out.println("end");
    }
}
