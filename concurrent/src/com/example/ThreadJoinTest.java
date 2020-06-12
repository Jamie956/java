package com.example;

public class ThreadJoinTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("start");
        Task t = new Task();
        t.start();
//        t.join();
        t.join(1000);
        System.out.println("end");
    }
}

class Task extends Thread {
    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            System.out.println("->: "+Thread.currentThread().getName());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}