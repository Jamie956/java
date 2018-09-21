package com.example;

public abstract class Tobacco {
	public void smoke(Wizard wizard) {
		System.out.println(wizard.getClass().getSimpleName() + " smoking " + this.getClass().getSimpleName());
	}
}
