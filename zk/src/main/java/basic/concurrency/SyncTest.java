package basic.concurrency;

public class SyncTest {
    //    public static void work(){
//        Object ob = new ObjectThreadMethodTest();
//        try {
//            System.out.println(1);
//            ob.wait(1000);
//            System.out.println(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//    public static void main(String[] args) {
//        new Thread(()->work()).start();
//
//
//    }


    // one object multi Thread sync method
    public static void test1() {
        SyncTestObject obj = new SyncTestObject();
        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.a();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.a();
            }
        }).start();
    }

    //diff object
    public static void test2() {
        SyncTestObject x = new SyncTestObject();
        SyncTestObject y = new SyncTestObject();

        new Thread(new Runnable() {
            @Override
            public void run() {
                x.a();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                y.a();
            }
        }).start();
    }

    public static void test3() {
        SyncTestObject obj = new SyncTestObject();
        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.a();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.b();
            }
        }).start();
    }

    public static void test4() {
        SyncTestObject obj = new SyncTestObject();
        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.a();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.c();
            }
        }).start();
    }

    public static void test5() {
        SyncTestObject x = new SyncTestObject();
        SyncTestObject y = new SyncTestObject();

        new Thread(new Runnable() {
            @Override
            public void run() {
                x.d();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                y.e();
            }
        }).start();
    }

    public static void test6() {
        SyncTestObject x = new SyncTestObject();

        new Thread(new Runnable() {
            @Override
            public void run() {
                x.c();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                SyncTestObject.d();
            }
        }).start();
    }

    public static void main(String[] args) {
        test6();
    }
}
