package basic;

import basic.aenum.Color;
import basic.aenum.Season;
import basic.concurrency.SyncObject;
import basic.entity.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BasicMain {
    private int i = 1;

    /**
     * 泛型类
     */
    static class Point<T> {
        public T x;

        public void setX(T x) {
            this.x = x;
        }
    }

    /**
     * 泛型方法
     */
    public static <E> void test2(E[] x) {
    }

    /**
     * 泛型方法 extends
     */
    public static <T extends Comparable<T>> T maximum(T x, T y, T z) {
        T max = x;
        if (y.compareTo(max) > 0) {
            max = y;
        }
        if (z.compareTo(max) > 0) {
            max = z;
        }
        return max;
    }

    /**
     * 接口泛型
     */
    interface Cat<V> {
        public V pr();
    }

    /**
     * 非静态成员类，可以访问成员变量
     */
    class A {
        A() {
            System.out.println(i);
        }
    }

    /**
     * 方法+内部类，可以访问成员变量
     *
     * @return
     */
    public List innerClass() {
        class C {
            private int i = 2;

            C() {
                System.out.println(i);
            }
        }
        return null;
    }

    /**
     * 静态成员类+内部类，不能访问成员变量，但可以访问静态变量
     */
    static class B {
        B() {
            System.out.println("create B");
        }
    }

    /**
     * 静态方法+内部类，不能访问成员变量
     *
     * @return
     */
    public static List staticInnerClass() {
        class C {
            C() {
                System.out.println("create C");
            }
        }
        return null;
    }

    @MyAnnotation(value = "a")
    static class TestAnno {
    }


    @Test
    public void testStatic() {
        //非静态
        new BasicMain().new A();
        new BasicMain().innerClass();

        //静态
        new B();
        BasicMain.staticInnerClass();
    }

    /**
     * 获取注解
     */
    @Test
    public void annotationTest() {
        MyAnnotation anno = TestAnno.class.getAnnotation(MyAnnotation.class);
        String a = anno.value();
    }

    /**
     * 字符串格式化
     * 补零
     */
    @Test
    public void stringMethod() {
        String a = String.format("halo %s ", 1);
        String b = StringUtils.rightPad("440", 8, "0");
    }

    /**
     * 运行时异常
     * 1.比如校验数据不正确，在运行时抛出异常
     */
    @Test
    public void runtimeException() {
        throw new RuntimeException();
//        throw new IndexOutOfBoundsException("111");
    }

    /**
     * 受检异常
     * 1.编译时检查，需要捕获的异常，可能发生的异常
     */
    @Test
    public void checkException() {
        try {
            throw new IOException();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 泛型擦除
     */
    @Test
    public void genClean() {
        Class<?> class1 = new ArrayList<String>().getClass();
        Class<?> class2 = new ArrayList<Integer>().getClass();
        System.out.println(class1.equals(class2));
    }

    /**
     * ArrayList 构造函数测试
     * 1.无参，初始共享空数组实例
     * 2.容量参数，创建指定大小object实例
     */
    @Test
    public void listInit() {

        new ArrayList<>();
        new ArrayList<>(5);

        LinkedList list = new LinkedList<>();
        list.add(1);
        list.add(2);
        new ArrayList<>(list);

    }

    /**
     * 扩容测试
     * 1.第一次添加元素，使用默认容量10
     * 2.超过容量扩容增加原来的0.5倍
     */
    @Test
    public void listCapacity() {
        ArrayList list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);

    }

    /**
     * 指定索引位置插入
     * 1.边界检测
     * 2.数组复制, source index+1, length size-index
     */
    @Test
    public void listIndex() {
        ArrayList list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);

        list.add(1, "a");
    }

    /**
     * 遍历器测试
     * 1.指针检测是否还有下一个元素
     * 2.读取指针元素，移动指针
     */
    @Test
    public void listForeach() {
        ArrayList list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);

        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    /**
     * linked list 构造函数
     */
    @Test
    public void listConstruct() {
        new LinkedList();
    }

    /**
     * linked list CRUD test
     * 1.指针连接
     * 2.节点数据结构，前后双指针
     * 3.移除，断开连接
     */
    @Test
    public void listRemove() {
        LinkedList list = new LinkedList<>();
        list.add(1);
        list.add(2);

        list.remove();
    }

    /**
     * Set 构造实验
     * 1.hashmap存储元素
     * 2.无参，构造函数初始默认load factor
     * 3.集合参数，size小于12时使用默认容量16
     * 4.容量，加载因子参数
     */
    @Test
    public void setConstruct() {
        new HashSet<>();
        new HashSet<>(Arrays.asList(1, 2, 3));
        new HashSet<>(12, 0.8f);
    }

    /**
     * set add element
     */
    @Test
    public void setAdd() {
        HashSet set = new HashSet();
        set.add(1);
        set.add(2);
        set.add(1);
    }


    /**
     * hash map test
     * 1.构造函数初始load factor
     * 2.元素储存在table数组
     * 3.节点结构，hash, key, val, next
     */
    @Test
    public void mapPut() {
        HashMap map = new HashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
    }

    /**
     * 静态代码块、代码块、构造方法 执行顺序
     * 执行父类子类的静态代码块 -> 父类代码块，构造方法 -> 子类代码块，构造方法
     */
    @Test
    public void testOrder() {
        new InitOrderB();
    }

    private static int hash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash ^ str.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        if (hash < 0) {
            hash = Math.abs(hash);

        }
        return hash;
    }

    /**
     * 一致性hash环，根据用户ip路由到不同服务器地址（不带虚拟节点）
     * 1. 初始化加载服务器地址，装在treemap里，key->hash, value->真实地址
     * 2. 用户根据ip得到对应hash，treemap.get(hash(userIp)) 获取服务器列表，获取顺时针最靠近的值
     */
    @Test
    public void consistentHashing() {
        SortedMap<Integer, String> sortedMap = new TreeMap<>();

        String[] servers = {"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111", "192.168.0.3:111", "192.168.0.4:111"};

        for (int i = 0; i < servers.length; i++) {
            int hash = hash(servers[i]);
            sortedMap.put(hash, servers[i]);
        }

        SortedMap<Integer, String> subMap = sortedMap.tailMap(hash("127.0.0.1:1111"));
        String a = subMap.get(subMap.firstKey());
    }

    /**
     * JDK代理
     */
    @Test
    public void jdkproxytest() {
        DynamicProxy dp = new DynamicProxy(new HelloImpl());
        IHello helloProxy = dp.getProxy();

        helloProxy.greeting();
    }

    /**
     * test cglib
     */
    @Test
    public void cglib() {
        basic.entity.IHello helloProxy = CGLibProxy.getInstance().getProxy(basic.entity.HelloImpl.class);
        helloProxy.greeting();
    }

    /**
     * 深克隆，引用类型也会被克隆
     */
    @Test
    public void deepClone() {
        try {
            User user = new User(new Address("stress1"));
            User clone = (User) BasicMain.deepClone(user);

            System.out.println(user == clone);
            System.out.println(user.getAddress() == clone.getAddress());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object deepClone(Object object) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(object);

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        return ois.readObject();
    }

    /**
     * values()，获取全部枚举成员数组
     */
    @Test
    public void enumTest() {
        for (Season season : Season.values()) {
            switch (season) {
                case SPRING:
                    System.out.println("name: " + season.name() + " code: " + season.getCode());
                    break;
                case AUTUMN:
                    System.out.println("name: " + season.name() + " code: " + season.getCode());
                    break;
                case SUMMER:
                    System.out.println("name: " + season.name() + " code: " + season.getCode());
                    break;
                case WINTER:
                    System.out.println("name: " + season.name() + " code: " + season.getCode());
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * valueOf()，根据枚举名字，获取指定的枚举实例
     * ordinal()，获取枚举的索引
     */
    @Test
    public void enumTest2() {
        Season a = Season.valueOf("SPRING");
        Season c = Season.SPRING;
        int b = Season.valueOf("SUMMER").ordinal();
    }

    /**
     *
     */
    @Test
    public void enumtest3() {

        String value = Color.getValueByName("red");
    }

    /**
     * 反射获取成员变量、修改成员变量
     */
    @Test
    public void reflectFields() throws NoSuchFieldException, IllegalAccessException {
        Person person = new Person();
        Field nameField = person.getClass().getDeclaredField("name");
        nameField.setAccessible(true);

        String a = (String) nameField.get(person);
        nameField.set(person, "jim");
        String b = (String) nameField.get(person);
    }

    /**
     * 浅克隆，不克隆引用类型
     */
    @Test
    public void shallowClone() {
        try {
            Student student = new Student(new Address("stress"));
            Student clone = (Student) student.clone();

            System.out.println(student == clone);
            System.out.println(student.getAddress() == clone.getAddress());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void objectIO() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(new Person("tom"));

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Person person = (Person) ois.readObject();
            System.out.println(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void charO() {
        Writer out = null;
        try {
            out = new FileWriter("a");
            out.write("hi");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void charIO2() {
        Reader in = null;
        Writer out = null;
        try {
            in = new FileReader("a");
            out = new FileWriter("b");
            char[] cbuf = new char[10];
            int len = -1;

            while ((len = in.read(cbuf)) != -1) {
                out.write(cbuf, 0, len);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //file output
    @Test
    public void fileOut() {
        OutputStream out = null;
        try {
            out = new FileOutputStream("a");
            out.write(new byte[]{65, 66, 67});
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //file input and output
    @Test
    public void fileIO() {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream("a");
            out = new FileOutputStream("b");
            /**
             * way1, read byte by byte
             */
            int b = 0;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            /**
             * way2, read by bytes[]
             */
//            byte[] b = new byte[20];
//            int len = -1;
//            while ((len = in.read(b)) != -1) {
//                out.write(b, 0, len);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void notBlockingServer() {
        try {
            ServerSocket ss = new ServerSocket(8081);

            while (true) {
                Socket socket = ss.accept();
                OutputStream out = socket.getOutputStream();
                out.write("hi".getBytes());

                out.close();
                socket.close();
            }

//            ss.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void notBlockingClient() {
        try {
            Socket socket = new Socket("127.0.0.1", 8081);
            InputStream in = socket.getInputStream();
            Scanner scan = new Scanner(in);

            while (scan.hasNext()) {
                System.out.println(scan.next());
            }

            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 多线程执行同一实例的带同一把锁的方法，顺序执行
     */
    @Test
    public void synctest1() throws InterruptedException {
        SyncObject obj = new SyncObject();
        new Thread(() -> obj.syncMethod()).start();
        new Thread(() -> obj.syncMethod()).start();

        //当前线程等待线程t1和t2执行完成
        Thread.sleep(3000);
    }

    /**
     * 多线程执行不同实例的带不同锁的方法，不按顺序执行
     */
    @Test
    public void synctest2() throws InterruptedException {
        SyncObject x = new SyncObject();
        SyncObject y = new SyncObject();

        new Thread(() -> x.syncMethod()).start();
        new Thread(() -> y.syncMethod()).start();

        //当前线程等待线程t1和t2执行完成
        Thread.sleep(3000);
    }

    /**
     * 多线程执行同一个实例的带锁方法和不带锁方法，不按顺序执行
     */
    @Test
    public void synctest3() throws InterruptedException {
        SyncObject obj = new SyncObject();
        new Thread(() -> obj.syncMethod()).start();
        new Thread(() -> obj.notSyncMethod()).start();

        //当前线程等待线程t1和t2执行完成
        Thread.sleep(3000);
    }

    /**
     * 多线程执行同一实例的带同一把锁的方法，顺序执行
     */
    @Test
    public void synctest4() throws InterruptedException {
        SyncObject obj = new SyncObject();
        new Thread(() -> obj.syncMethod()).start();
        new Thread(() -> obj.syncMethod2()).start();

        //当前线程等待线程t1和t2执行完成
        Thread.sleep(3000);
    }

    /**
     * 多线程执行带锁的静态方法，顺序执行
     * 类锁/全局锁
     */
    @Test
    public void synctest5() throws InterruptedException {
        new Thread(() -> SyncObject.staticSyncMethod()).start();
        new Thread(() -> SyncObject.staticSyncMethod2()).start();

        //当前线程等待线程t1和t2执行完成
        Thread.sleep(3000);
    }

    /**
     * 多线程执行实例的带锁方法和静态带锁方法，不按顺序执行
     */
    @Test
    public void synctest6() throws InterruptedException {
        SyncObject x = new SyncObject();
        new Thread(() -> x.syncMethod2()).start();
        new Thread(() -> SyncObject.staticSyncMethod()).start();

        //当前线程等待线程t1和t2执行完成
        Thread.sleep(3000);
    }

    /**
     * TimeUnit的 sleep方法
     */
    @Test
    public void timeunitTes() throws InterruptedException {
        System.out.println("sleep begin");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("sleep end");
    }

    /**
     * timedWait 带锁方法，获取锁等待
     */
    public synchronized void work() {
        System.out.println("Begin Work");
        try {
            TimeUnit.SECONDS.timedWait(this, 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Work End");
    }

    /**
     * atom
     */
    @Test
    public void atom() throws InterruptedException {
        AtomicInteger sharedValue = new AtomicInteger();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    sharedValue.incrementAndGet();
                }
            }).start();
        }

        Thread.sleep(3000);
        System.out.println(sharedValue.get());
    }

    /**
     * future task
     */
    @Test
    public void futureTaskTest() {
        try {
            FutureTask<Integer> futureTask = new FutureTask<>(() -> 1);
            new Thread(futureTask).start();
            int a = futureTask.get();
            System.out.println();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void futureTaskTestPool() {
        try {
            FutureTask<Integer> futureTask = new FutureTask<>(() -> 1);
            ExecutorService pool = Executors.newSingleThreadExecutor();
            pool.submit(futureTask);
            int a = futureTask.get();
            pool.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 排序
     */
    @Test
    public void orderingDouble() {
        //对象排序
//        List<Person> ps = Arrays.asList(new Person("Lord of the rings", 8.8), new Person("Back to the future", 8.5), new Person("Carlito's way", 7.9), new Person("Pulp fiction", 8.9));
//        ps.sort(Comparator.comparingDouble(Person::getLength).reversed());
//        ps.forEach(System.out::println);

        //数组排序
//        Double[] doubles = {0.06, 0.01, 0.30, 0.20, 0.25};
//        Arrays.sort(doubles);
    }

    /**
     * 集合排序
     */
    public void listdoubleorder() {
        List<JSONObject> list = new ArrayList<>();

        JSONObject json1 = new JSONObject();
        json1.put("name", "a");
        json1.put("value", 0.01d);

        JSONObject json2 = new JSONObject();
        json2.put("name", "b");
        json2.put("value", 0.06d);

        JSONObject json3 = new JSONObject();
        json3.put("name", "c");
        json3.put("value", 0.30d);

        list.add(json1);
        list.add(json2);
        list.add(json3);

//        list.sort((o1, o2) -> {
//            double value1 = o1.getDoubleValue("value") * 10000;
//            double value2 = o2.getDoubleValue("value") * 10000;
//            return (int) (value2 - value1);
//        });

//        list.sort((a, b) -> (int)(a.getDoubleValue("value") - b.getDoubleValue("value")));

        //升序
        list.sort(Comparator.comparingDouble(e -> e.getDoubleValue("value")));

        //降序
//        list.sort(Comparator.comparingDouble((JSONObject e) -> e.getDoubleValue("value")).reversed());
    }
}



