package com.example.v1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.example.v1");
		context.refresh();
		
		Book book = context.getBean(Book.class);
		System.out.println(book);
		
		context.close();
	}
}