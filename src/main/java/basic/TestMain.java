package basic;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.awt.*;
import java.awt.List;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

public class TestMain{
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
    public static <E> void test2(E[] x) {}

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
    public List test() {
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
    public static List innerClass() {
        class C extends List {
            C() {
                System.out.println("create C");
            }
        }
        return new C();
    }

    @MyAnnotation(value="a")
    static class TestAnno{}


    @Test
    public void testStatic() {
        //非静态
        new TestMain().new A();
        new TestMain().test();

        //静态
        new B();
        TestMain.innerClass();
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
     */
    @Test
    public void asd() {
        String a = String.format("halo %s ", 1);
    }

    /**
     * 补零
     */
    @Test
    public void asasdasdd() {
        String a = StringUtils.rightPad("440", 8, "0");
    }

    /**
     * 运行时异常
     * 1.比如校验数据不正确，在运行时抛出异常
     */
    @Test
    public void extest() {
        throw new RuntimeException();
//        throw new IndexOutOfBoundsException("111");
    }

    /**
     * 受检异常
     * 1.编译时检查，需要捕获的异常，可能发生的异常
     */
    @Test
    public void extest22() {
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
    public void test1() {

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
    public void test2() {
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
    public void test3() {
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
    public void test4() {
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
    public void test5() {
        new LinkedList();
    }

    /**
     * linked list CRUD test
     * 1.指针连接
     * 2.节点数据结构，前后双指针
     * 3.移除，断开连接
     */
    @Test
    public void test6() {
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
    public void test7() {
        new HashSet<>();
        new HashSet<>(Arrays.asList(1,2,3));
        new HashSet<>(12, 0.8f);
    }

    /**
     * set add element
     */
    @Test
    public void test8(){
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
    public void test9(){
        new TreeSet<>();

    }

    /**
     * hash map test
     * 1.构造函数初始load factor
     * 2.元素储存在table数组
     * 3.节点结构，hash, key, val, next
     */
    @Test
    public void test10(){
        HashMap map = new HashMap<>();
        map.put("k1","v1");
        map.put("k2","v2");
    }

    /**
     * 静态代码块、代码块、构造方法 执行顺序
     */
    @Test
    public void tesasd() {
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
    public void consistentHashingWithoutVirtualNode() {
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
    public void proxytest() {
        DynamicProxy dp = new DynamicProxy(new HelloImpl());
        IHello helloProxy = dp.getProxy();

        helloProxy.greeting();
    }
}

class InitOrder {
    static {
        System.out.println("1");
    }

    {
        System.out.println("2");
    }

    InitOrder() {
        System.out.println("3");
    }
}

class InitOrderB extends InitOrder {
    static {
        System.out.println("4");
    }

    {
        System.out.println("5");
    }

    InitOrderB() {
        System.out.println("6");
    }
}