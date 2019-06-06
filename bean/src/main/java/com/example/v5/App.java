package com.example.v5;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.example");
		context.refresh();

		AnimalService animalService = context.getBean(AnimalService.class);

		animalService.printCat();
		animalService.printDog();
		
		context.close();
	}
}