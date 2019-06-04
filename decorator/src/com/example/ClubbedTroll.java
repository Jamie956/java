package com.example;

public class ClubbedTroll implements Troll {
	private Troll decorated;

	public ClubbedTroll(Troll decorated) {
		this.decorated = decorated;
	}

	@Override
	public void attack() {
		decorated.attack();
		System.out.println("C");
	}

	@Override
	public int getAttackPower() {
		return decorated.getAttackPower() + 10;
	}

	@Override
	public void fleeBattle() {
		decorated.fleeBattle();
	}
}
