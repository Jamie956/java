package com.example.factorymethod;

public class CatFactory implements AnimalFactory {
	@Override
	public Animal getAnimal() {
		return new Cat();
	}
}