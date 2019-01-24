package com.example.v1;

public class App {
	public static void main(String[] args) {
		test02();
	}

	public static void test01() {
		Sword sword = new Sword(new SoulEatingEnchantment());
		System.out.println(sword.getEnchantment());
		sword.wield();
		sword.swing();
		sword.unwield();
	}

	public static void test02() {
		Hammer hammer = new Hammer(new FlyingEnchantment());
		System.out.println(hammer.getEnchantment());
		hammer.wield();
		hammer.swing();
		hammer.unwield();
	}

}
