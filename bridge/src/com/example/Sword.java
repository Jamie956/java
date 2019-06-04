package com.example;

public class Sword {
	private final SoulEatingEnchantment enchantment;

	public Sword(SoulEatingEnchantment enchantment) {
		this.enchantment = enchantment;
	}

	public void wield() {
		System.out.println("The sword is wielded.");
		enchantment.onActivate();
	}

	public void swing() {
		System.out.println("The sword is swinged.");
		enchantment.apply();
	}

	public void unwield() {
		System.out.println("The sword is unwielded.");
		enchantment.onDeactivate();
	}

}
