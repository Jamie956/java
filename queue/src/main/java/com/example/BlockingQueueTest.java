package com.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class BlockingQueueTest {
	public static class Basket {
		BlockingQueue<String> basket = new ArrayBlockingQueue<String>(3);

		public void produce() throws InterruptedException {
			basket.put("An apple");
		}

		public String consume() throws InterruptedException {
			String apple = basket.take();
			return apple;
		}

		public int getAppleNumber() {
			return basket.size();
		}
	}

	public static void testBasket() {
		final Basket basket = new Basket();
		class Producer implements Runnable {
			public void run() {
				try {
					while (true) {
						System.out.println("ready to create apple: " + System.currentTimeMillis());
						basket.produce();
						System.out.println("created apple: " + System.currentTimeMillis());
						System.out.println("apple num after created: " + basket.getAppleNumber());
						Thread.sleep(300);
					}
				} catch (InterruptedException ex) {
				}
			}
		}
		
		class Consumer implements Runnable {
			public void run() {
				try {
					while (true) {
						System.out.println("ready to consume apple: " + System.currentTimeMillis());
						basket.consume();
						System.out.println("consumed apple: " + System.currentTimeMillis());
						System.out.println("apple num after consumed: " + basket.getAppleNumber());
						Thread.sleep(1000);
					}
				} catch (InterruptedException ex) {
				}
			}
		}

		ExecutorService service = Executors.newCachedThreadPool();
		Producer producer = new Producer();
		Consumer consumer = new Consumer();
		service.submit(producer);
		service.submit(consumer);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
		}
		service.shutdownNow();
	}

	public static void main(String[] args) {
		BlockingQueueTest.testBasket();
	}
}