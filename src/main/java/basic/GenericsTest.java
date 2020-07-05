package basic;

import java.util.ArrayList;

public class GenericsTest {

    public static void main(String[] args) {
        test1();
    }

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
     * 泛型擦除
     */
    public static void test1() {
        Class<?> class1 = new ArrayList<String>().getClass();
        Class<?> class2 = new ArrayList<Integer>().getClass();
        System.out.println(class1);
        System.out.println(class2);
        System.out.println(class1.equals(class2));
    }
}

