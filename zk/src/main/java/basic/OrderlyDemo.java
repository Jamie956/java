package basic;


import java.time.LocalDateTime;

/**
 * @author ：mmzsblog
 * @description：并发中的有序性问题
 * @date ：2020年2月26日 15:22:05
 */
public class OrderlyDemo {

    static int value = 1;
    private static boolean flag = false;

    public static void task1() {
        value = 1024;
//        System.out.println(flag);
        flag = true;
    }

    public static void task2() {
        if (flag) {
//            System.out.println(flag);
            System.out.println("flag true, value "+value);
        }
//        else {
//            System.out.println("flag false, value "+value);
//        }
    }

    public static void main(String[] args) {
//        {
            for (int i = 0; i < 199; i++) {
                value = 1;
                flag = false;
//            Thread thread1 = new DisplayThread();
//            Thread thread2 = new CountThread();
//            thread1.start();
//            thread2.start();

                new Thread(() -> task1()).start();
                new Thread(() -> task2()).start();


                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("=========================================================");

//            }

        }
    }
}
//    public static void main(String[] args)

//    static class DisplayThread extends Thread {
//        @Override
//        public void run() {
////            System.out.println(Thread.currentThread().getName() + " DisplayThread begin, time:" + LocalDateTime.now());
//            value = 1024;
////            System.out.println(Thread.currentThread().getName() + " change flag, time:" + LocalDateTime.now());
//            flag = true;
////            System.out.println(Thread.currentThread().getName() + " DisplayThread end, time:" + LocalDateTime.now());
//        }
//    }

//    static class CountThread extends Thread {
//        @Override
//        public void run() {
//            if (flag) {
//                System.out.println(Thread.currentThread().getName() + " value的值是：" + value + ", time:" + LocalDateTime.now());
//                System.out.println(Thread.currentThread().getName() + " CountThread flag is true,  time:" + LocalDateTime.now());
//            } else {
//                System.out.println(Thread.currentThread().getName() + " value的值是：" + value + ", time:" + LocalDateTime.now());
//                System.out.println(Thread.currentThread().getName() + " CountThread flag is false, time:" + LocalDateTime.now());
//            }
//        }
//    }
//}