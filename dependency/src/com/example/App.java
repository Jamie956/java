package com.example;

import com.example.bean.Deer;
import com.example.bean.Dog;
import com.example.factory.ObjectFactory;
import com.example.service.AnimalService;

public class App {
	public static void main(String[] args) {
		AnimalService animalService = (AnimalService) ObjectFactory.getInstance("AnimalService.class");
		
		Dog dog = (Dog) ObjectFactory.getInstance("Dog.class");
		animalService.setAnimal(dog);
		animalService.sayhi();
		
		Deer deer = (Deer) ObjectFactory.getInstance("Deer.class");
		animalService.setAnimal(deer);
		animalService.sayhi();
	}
}