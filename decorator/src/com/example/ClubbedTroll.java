package com.example;

import java.util.logging.Logger;

public class ClubbedTroll implements Troll {

	private static final Logger LOGGER = Logger.getLogger("ClubbedTroll");

	private Troll decorated;

	public ClubbedTroll(Troll decorated) {
		this.decorated = decorated;
	}

	@Override
	public void attack() {
		decorated.attack();
		LOGGER.info("The troll swings at you with a club!");
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
