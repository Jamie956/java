package com.example.v7;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.example.v2");
		context.refresh();
		
		context.getBean(Item.class);
		
		context.close();
	}
}
