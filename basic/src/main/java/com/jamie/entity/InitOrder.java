package com.jamie.entity;

public class InitOrder {
    static {
        System.out.println("1父类静态代码块");
    }

    {
        System.out.println("3父类代码块");
    }

    InitOrder() {
        System.out.println("5父类构造函数");
    }
}
