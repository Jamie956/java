package com.example;

public class CatFactory implements AnimalFactory {
	@Override
	public Animal getAnimal(AnimalType animalType) {
		return new Cat(animalType);
	}
}
