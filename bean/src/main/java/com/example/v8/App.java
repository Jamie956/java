package com.example.v8;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

//		context.getBean("bookBean");
//		context.getBean("bookBeanLazy");

		context.close();
	}
}
