package com.example;

public class SoulEatingEnchantment {
	public void onActivate() {
		System.out.println("The item spreads bloodlust.");
	}

	public void apply() {
		System.out.println("The item eats the soul of enemies.");
	}

	public void onDeactivate() {
		System.out.println("Bloodlust slowly disappears.");
	}
	
}
