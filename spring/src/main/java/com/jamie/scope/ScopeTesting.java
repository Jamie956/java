package com.jamie.scope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ScopeTesting {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        System.out.println(context.getBean("getWindow") == context.getBean("getWindow"));

        context.close();
    }
}
