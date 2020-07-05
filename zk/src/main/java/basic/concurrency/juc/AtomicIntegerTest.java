package basic.concurrency.juc;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
    public static AtomicInteger sharedValue = new AtomicInteger();

    public static void increment() {
        for (int i = 0; i < 10; i++) {
            sharedValue.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> increment()).start();
        }

        System.out.println(sharedValue.get());
    }
}