package com.example;

public class App {
	public static void main(String[] args) {
		Sword sword = new Sword(new SoulEatingEnchantment());
		sword.wield();
		sword.swing();
		sword.unwield();
	}
}
