package com.example.v5;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.example.v5");
		context.refresh();

		Animal animal = context.getBean(Animal.class);
		animal.say();
		
		context.close();
	}
}