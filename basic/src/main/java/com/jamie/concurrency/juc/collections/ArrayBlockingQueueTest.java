package com.jamie.concurrency.juc.collections;

import org.junit.Test;

import java.util.concurrent.*;

public class ArrayBlockingQueueTest {
    public static ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 10, 1, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(5), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    /**
     * 往满的数组阻塞队列 添加元素
     * <p>
     * 设数组队列长度为2，当添加第三个元素时，
     * 数组队列满了（count == items.length，即队列存在元素个数等于数组长度），
     * 发生队列阻塞（notFull Condition await），锁自旋（while ()），
     * 等到从队列take 上锁取走1个元素，notFull Condition signal，
     * 不再自旋，而是插入数组元素，notEmpty Condition signal
     */
    @Test
    public void blockingQueuePutTest() {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);

        pool.execute(() -> {
            try {
                queue.put(1);
                queue.put(2);
                queue.put(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        pool.execute(() -> {
            try {
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        queue.forEach(System.out::println);
    }

    /**
     * 在空的数组阻塞队列 取出元素
     * <p>
     * 空队列取元素，取数等待（Condition notEmpty）await,
     * 当往队列添加元素时，队列元素个数 count+1，并 通知取数（Condition notEmpty）signal，
     */
    @Test
    public void blockQueueTakeTest() {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);

        pool.execute(() -> {
            try {
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        pool.execute(() -> {
            try {
                queue.put(1);
                queue.put(2);
                queue.put(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        queue.forEach(System.out::println);
    }

}
