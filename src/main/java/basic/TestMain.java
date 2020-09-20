package basic;

import basic.aenum.Color;
import basic.aenum.Season;
import basic.entity.*;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.awt.List;
import java.io.*;
import java.lang.reflect.Field;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class TestMain {
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
        class C extends List {
            private int i = 2;

            C() {
                System.out.println(i);
            }
        }
        return new C();
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
        class C extends List {
            C() {
                System.out.println("create C");
            }
        }
        return new C();
    }

    @MyAnnotation(value = "a")
    static class TestAnno {
    }


    @Test
    public void testStatic() {
        //非静态
        new TestMain().new A();
        new TestMain().innerClass();

        //静态
        new B();
        TestMain.staticInnerClass();
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
     * 2.数组复制, from index+1, length size-index
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
     * tree set 构造函数测试
     * 1.创建tree map
     */
    @Test
    public void test9() {
        new TreeSet<>();

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
            User clone = (User) TestMain.deepClone(user);

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


}



