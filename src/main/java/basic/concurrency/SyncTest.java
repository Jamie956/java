package basic.concurrency;

public class SyncTest {
    /**
     * 多线程执行同一实例的带同一把锁的方法，顺序执行
     */
    public static void test1() {
        SyncObject obj = new SyncObject();
        new Thread(() -> obj.a()).start();
        new Thread(() -> obj.a()).start();
    }

    /**
     * 多线程执行不同实例的带不同锁的方法，不按顺序执行
     */
    public static void test2() {
        SyncObject x = new SyncObject();
        SyncObject y = new SyncObject();

        new Thread(() -> x.a()).start();
        new Thread(() -> y.a()).start();
    }

    /**
     * 多线程执行同一个实例的带锁方法和不带锁方法，不按顺序执行
     */
    public static void test3() {
        SyncObject obj = new SyncObject();
        new Thread(() -> obj.a()).start();
        new Thread(() -> obj.b()).start();
    }

    /**
     * 多线程执行同一实例的带同一把锁的方法，顺序执行
     */
    public static void test4() {
        SyncObject obj = new SyncObject();
        new Thread(() -> obj.a()).start();
        new Thread(() -> obj.c()).start();
    }

    /**
     * 多线程执行带锁的静态方法，顺序执行
     * 类锁/全局锁
     */
    public static void test5() {
        new Thread(() -> SyncObject.d()).start();
        new Thread(() -> SyncObject.e()).start();
    }

    /**
     * 多线程执行实例的带锁方法和静态带锁方法，不按顺序执行
     */
    public static void test6() {
        SyncObject x = new SyncObject();
        new Thread(() -> x.c()).start();
        new Thread(() -> SyncObject.d()).start();

    }

    public static void main(String[] args) {
        test5();
    }
}
