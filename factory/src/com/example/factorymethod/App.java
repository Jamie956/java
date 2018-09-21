package com.example.factorymethod;

public class App {
	public static void main(String[] args) {
		AnimalFactory catFactory = new CatFactory();
		Animal cat = catFactory.getAnimal();
		cat.eat();
	}
}
