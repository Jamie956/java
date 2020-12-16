package com.jamie.bean;

import com.jamie.bean.annotation.AppConfig;
import com.jamie.bean.annotation.Book;
import com.jamie.bean.xml.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCTest {

    //component annotation and autowired inject dependency field
    public static void test2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.example.bean.annotation");
        context.refresh();

        context.getBean(Book.class);

        context.close();
    }

    //configuration annotation scan and bean IOC
    public static void test3() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        context.getBean("person");

        context.close();
    }

    //IOC - base XML
    public static void test4(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ioc-bean.xml");

        User user = (User) context.getBean("user");
        user.show();

        context.close();
    }

    public static void main(String[] args) {
        test4();
    }
}