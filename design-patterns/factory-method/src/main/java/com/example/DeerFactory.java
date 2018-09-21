package com.example;

public class DeerFactory implements AnimalFactory {
	@Override
	public Animal getAnimal(AnimalType animalType) {
		return new Deer(animalType);
	}
}
