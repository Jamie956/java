package basic.concurrency;

import java.util.concurrent.TimeUnit;

public class JoinTest {

    /**
     * t.join();//当前线程等待线程t挂掉
     *
     * t.join(1000);//当前线程等待线程t 1s
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("start");
        Thread t = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        });
        t.start();

        t.join();
//        t.join(1000);
//        t.join(4000);

        System.out.println("end");
    }

}
