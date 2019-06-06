package com.example.v5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {
	@Autowired
	@Qualifier("CatBean")
	private Cat cat;
	
	@Autowired
	@Qualifier("DogBean")
	private Dog dog;

	public void printCat() {
		cat.sayhi();
	}
	
	public void printDog() {
		dog.sayhi();
	}
}