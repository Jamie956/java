package basic;

import sun.reflect.generics.tree.Tree;

import java.lang.reflect.Array;
import java.util.*;

public class ContainerTest {

    /**
     * ArrayList 构造函数测试
     * 1.无参，初始共享空数组实例
     * 2.容量参数，创建指定大小object实例
     */
    private static void test1() {

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
    public static void test2() {
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
    public static void test3() {
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
    public static void test4() {
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
    public static void test5() {
        new LinkedList();
    }

    /**
     * linked list CRUD test
     * 1.指针连接
     * 2.节点数据结构，前后双指针
     * 3.移除，断开连接
     */
    public static void test6() {
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
    public static void test7() {
        new HashSet<>();

        int[] a = {1, 2, 3};
        List list = Arrays.asList(a);
        new HashSet<>(list);

        new HashSet<>(12, 0.8f);
    }

    /**
     * set add element test
     *
     */
    public static void test8(){
        HashSet set = new HashSet();
        set.add(1);
        set.add(2);
        set.add(1);
    }

    /**
     * tree set 构造函数测试
     * 1.创建tree map
     */
    public static void test9(){
        new TreeSet<>();

    }

    /**
     * hash map test
     * 1.构造函数初始load factor
     * 2.元素储存在table数组
     * 3.节点结构，hash, key, val, next
     */
    public static void test10(){
        HashMap map = new HashMap<>();
        map.put("k1","v1");
        map.put("k2","v2");
    }



    public static void main(String[] args) {
        test10();
    }
}
