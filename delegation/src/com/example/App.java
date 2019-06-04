package com.example;

public class App {

	public static void main(String[] args) {
		Cat c = new Cat();
		
		c.setSound(new Meow());
		c.makeSound();
		
		c.setSound(new Roar());
		c.makeSound();
	}

}
