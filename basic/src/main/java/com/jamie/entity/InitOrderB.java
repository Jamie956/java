package com.jamie.entity;

public class InitOrderB extends InitOrder {
    static {
        System.out.println("2子类静态代码块");
    }

    {
        System.out.println("4子类代码块");
    }

    public InitOrderB() {
        System.out.println("6子类构造函数");
    }
}
