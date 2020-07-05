package basic.concurrency.juc.collections;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;

public class COWArraySet {
    public static void work(Set<String> list) {
        for (int i = 1; i < 6; i++) {
            list.add(Thread.currentThread().getName() + "  " + i % 3);
        }
    }

    public static void main(String[] args) {
        Set<String> set = new CopyOnWriteArraySet<String>();

        new Thread(() -> work(set)).start();
        new Thread(() -> work(set)).start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Iterator iter = set.iterator();
        while (iter.hasNext()) {
            System.out.println((String) iter.next());
        }
    }

}
