package basic;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

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
    public void genClean() {
        Class<?> class1 = new ArrayList<String>().getClass();
        Class<?> class2 = new ArrayList<Integer>().getClass();
        System.out.println(class1.equals(class2));
    }

}

