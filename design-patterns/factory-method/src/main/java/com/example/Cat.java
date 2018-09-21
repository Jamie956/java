package com.example;

public class Cat implements Animal {
	private AnimalType animalType;

	public Cat(AnimalType animalType) {
		this.animalType = animalType;
	}

	@Override
	public String toString() {
		return "Cat type of " + animalType;
	}

	@Override
	public AnimalType getAnimalType() {
		return animalType;
	}
}
