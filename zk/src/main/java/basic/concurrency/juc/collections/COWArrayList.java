package basic.concurrency.juc.collections;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class COWArrayList {

    public static void task(List<String> list) {
        for (int i = 1; i < 6; i++) {
            list.add(Thread.currentThread().getName() + "  " + i);

            System.out.println("");
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                System.out.println((String) iter.next());
            }
        }
    }

    public static void main(String[] args) {

        List<String> list = new CopyOnWriteArrayList<String>();

        new Thread(() -> task(list)).start();
        new Thread(() -> task(list)).start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("*********Done*********");
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            System.out.println((String) iter.next());
        }
    }

}
