package com;

import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
//        invokeRPCByProxy();
        invokeRPC();
    }

    public static void invokeRPCByProxy() {
        Hello service = (Hello) Proxy.newProxyInstance(Hello.class.getClassLoader(), new Class<?>[]{Hello.class}, new ConsumerProxy<>());
        System.out.println(service.sayHello("tim"));
    }
    public static void invokeRPC() {
        System.out.println(new Consumer().invoke("com.HelloService", "sayHi"));
    }

}
