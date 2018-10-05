package com.example.service;

import com.example.bean.Animal;

public class AnimalService {
	private Animal animal;

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public void sayhi() {
		animal.sayhi();
	}
}