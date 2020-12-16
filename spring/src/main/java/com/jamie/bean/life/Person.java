package com.jamie.bean.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Person implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {

    private String name;

    public Person() {
        System.out.println("Person Construct");
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("setName()");
    }

    //自定义的初始化函数
    public void myInit() {
        System.out.println("myInit");
    }

    //自定义的销毁方法
    public void myDestroy() {
        System.out.println("myDestroy");
    }

    public void destroy() throws Exception {
        // TODO Auto-generated method stub
        System.out.println("destroy");
    }

    public void afterPropertiesSet() {
        // TODO Auto-generated method stub
        System.out.println("afterPropertiesSet");
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // TODO Auto-generated method stub
        System.out.println("setApplicationContext");
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        // TODO Auto-generated method stub
        System.out.println("setBeanFactory");
    }

    public void setBeanName(String beanName) {
        // TODO Auto-generated method stub
        System.out.println("setBeanName");
    }

    public String toString() {
        return "name is :" + name;
    }

}