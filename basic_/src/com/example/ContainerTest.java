package com.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

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

    public static void main(String[] args) {
        test5();
    }
}
