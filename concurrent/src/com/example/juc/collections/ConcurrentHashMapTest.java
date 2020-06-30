package com.example.juc.collections;


import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class ConcurrentHashMapTest {
    public static void work(ConcurrentHashMap map) {
        for (int i = 0; i < 6; i++) {
            map.put(i, Thread.currentThread().getName() + "-" + i);
        }
    }

    public static void main(String[] args) {
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<Integer, String>();

        new Thread(() -> work(map)).start();
        new Thread(() -> work(map)).start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}