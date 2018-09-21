package com.example;

public class Deer implements Animal {
	private AnimalType animalType;

	public Deer(AnimalType animalType) {
		this.animalType = animalType;
	}

	@Override
	public String toString() {
		return "Deer type of " + animalType;
	}

	@Override
	public AnimalType getAnimalType() {
		return animalType;
	}
}
