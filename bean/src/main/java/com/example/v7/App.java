package com.example.v7;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.example.v7");
		context.refresh();
		
		User u = context.getBean(User.class);
		System.out.println(u);
		
		context.close();
	}
}