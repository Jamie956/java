package com.jamie.bean;

import com.jamie.bean.life.Person;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LifeCycleTest {

    public static void main(String[] args) {

        System.out.println("开始初始化容器");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-life.xml");

        System.out.println("xml加载完毕");
        Person person = (Person) context.getBean("person");
        System.out.println(person);
        System.out.println("关闭容器");
        context.close();

    }

}