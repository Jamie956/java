package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.example");
		context.refresh();
		
		User u = context.getBean(User.class);
		System.out.println(u);
		
		context.close();
	}
}