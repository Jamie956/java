package basic.concurrency.juc.pool;

import java.text.MessageFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {
    public static LinkedBlockingQueue workQueue = new LinkedBlockingQueue<>(5);
    public static ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 2, 1, TimeUnit.MILLISECONDS, workQueue, Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    public static void work() {
        try {
            System.out.println(MessageFormat.format("{0} start: pool size={1} blocking queue size={2}", Thread.currentThread().getName(), pool.getPoolSize(), workQueue.size()));

            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        pool.execute(() -> work());

        pool.execute(() -> work());
        pool.execute(() -> work());
        pool.execute(() -> work());
        pool.execute(() -> work());
        pool.execute(() -> work());

        pool.execute(() -> work());

        pool.shutdown();
    }
}

