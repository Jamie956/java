package com.example.factorymethod;

public class DeerFactory implements AnimalFactory {
	@Override
	public Animal getAnimal(AnimalType animalType) {
		return new Deer(animalType);
	}
}
