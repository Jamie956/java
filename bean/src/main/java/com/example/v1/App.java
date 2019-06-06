package com.example.v1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// Scan component
public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.example.v1");
		context.refresh();
		
		context.getBean(Book.class);
		
		context.close();
	}
}