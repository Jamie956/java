package com.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerModelBlockQueueImpl {
    public static void main(String[] args) {
        final int maxSize = 10;
        BlockingQueue<Product> buffer = new LinkedBlockingQueue<Product>(maxSize);
        ExecutorService es = Executors.newFixedThreadPool(5);

        es.execute(new Producer(buffer));
        es.execute(new Producer(buffer));

        es.execute(new Consumer(buffer));
        es.execute(new Consumer(buffer));
        es.execute(new Consumer(buffer));

        es.shutdown();

    }

    static class Product {
        private String name;

        public Product(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    static class Producer implements Runnable {
        private BlockingQueue<Product> buffer;

        public Producer(BlockingQueue<Product> buffer) {
            this.buffer = buffer;
        }

        public void run() {
            while (true) {
                Product product = new Product("MAC");
                try {
                    buffer.put(product);
                    System.out.println("生产者[" + Thread.currentThread().getName() + "]生产了一个产品：" + product);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {
        private BlockingQueue<Product> buffer;

        public Consumer(BlockingQueue<Product> buffer) {
            this.buffer = buffer;
        }

        public void run() {
            while (true) {
                try {
                    System.out.println("消费者[" + Thread.currentThread().getName() + "]消费了一个产品：" + buffer.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
