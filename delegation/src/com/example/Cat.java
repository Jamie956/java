package com.example;

public class Cat {
	private Sound sound;

	public void makeSound() {
		this.sound.make();
	}

	public void setSound(Sound sound) {
		this.sound = sound;
	}
}