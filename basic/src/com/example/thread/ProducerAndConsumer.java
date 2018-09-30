package com.example.thread;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerAndConsumer {
	public static void main(String[] args) {
		test();
	}

	private static final long waitTime = 3000;

	private static void test() {
		Queue<Integer> queue = new LinkedList<>();// 队列对象,锁
		int maxsize = 2;// 队列中的最大元素个数限制

		// 下面4个线程，一瞬间只能有一个线程获得该对象的锁，进入同步代码块
		Producer producer = new Producer(queue, maxsize, "Producer");
		Consumer consumer1 = new Consumer(queue, maxsize, "Consumer1");
		Consumer consumer2 = new Consumer(queue, maxsize, "Consumer2");
		Consumer consumer3 = new Consumer(queue, maxsize, "Consumer3");

		producer.start();
		consumer2.start();
		consumer1.start();
		consumer3.start();
	}

	// 生产者线程
	public static class Producer extends Thread {
		Queue<Integer> queue;// queue,对象锁
		int maxsize;

		Producer(Queue<Integer> queue, int maxsize, String name) {
			this.queue = queue;
			this.maxsize = maxsize;
			this.setName(name);
		}

		@Override
		public void run() {
			while (true) {
				// 同步代码块,只有持有queue这个锁的对象才能访问这个代码块
				synchronized (queue) {
					try {
						Thread.sleep(waitTime);
						// sleep 线程阻塞，不会释放锁
						// wait 线程阻塞，会释放锁
					} catch (Exception e) {
					}

					System.out.println(this.getName() + "获得队列的锁");

					// 达到上限，wait等待
					while (queue.size() == maxsize) {
						System.out.println("队列已满，生产者" + this.getName() + "等待");
						try {
							queue.wait();// 让当前线程等待，直到其他线程调用notifyAll
						} catch (Exception e) {
						}
					}

					// 生产过程
					int num = (int) (Math.random() * 100);
					queue.offer(num);

					System.out.println(this.getName() + "生产一个元素：" + num);
					// 唤醒所有持有queue对象锁的正在等待的线程
					queue.notifyAll();
					System.out.println(this.getName() + "退出一次生产过程！");
				}
			}
		}
	}

	public static class Consumer extends Thread {
		Queue<Integer> queue;
		int maxsize;

		Consumer(Queue<Integer> queue, int maxsize, String name) {
			this.queue = queue;
			this.maxsize = maxsize;
			this.setName(name);
		}

		@Override
		public void run() {
			while (true) {
				synchronized (queue) {
					try {
						Thread.sleep(waitTime);
					} catch (Exception e) {
					}

					System.out.println(this.getName() + "获得队列的锁");
					while (queue.isEmpty()) {
						System.out.println("队列为空，消费者" + this.getName() + "等待");
						try {
							queue.wait();
						} catch (Exception e) {
						}
					}

					int num = queue.poll();
					System.out.println(this.getName() + "消费一个元素：" + num);
					// 唤醒所有线程,唤醒不会释放自己的锁
					queue.notifyAll();
					System.out.println(this.getName() + "退出一次消费过程！");
				}
			}
		}
	}
}
