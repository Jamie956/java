package com.example.v8;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

		Book b1 = (Book) context.getBean("bookBean");
		b1.setName("HTML");
		Book b2 = (Book) context.getBean("bookBean");

		System.out.println(b1.getName());
		System.out.println(b2.getName());

		Book s3 = (Book) context.getBean("bookBeanLazy");
		System.out.println(s3.getName());

		context.close();
	}
}
