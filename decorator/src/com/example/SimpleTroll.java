package com.example;

public class SimpleTroll implements Troll {

	@Override
	public void attack() {
		System.out.println("A");
	}

	@Override
	public int getAttackPower() {
		return 10;
	}

	@Override
	public void fleeBattle() {
		System.out.println("B");
	}
}
