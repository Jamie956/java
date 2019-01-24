package com.example;

import java.util.logging.Logger;

public class SimpleTroll implements Troll {

	private static final Logger LOGGER = Logger.getLogger("SimpleTroll");

	@Override
	public void attack() {
		LOGGER.info("The troll tries to grab you!");
	}

	@Override
	public int getAttackPower() {
		return 10;
	}

	@Override
	public void fleeBattle() {
		LOGGER.info("The troll shrieks in horror and runs away!");
	}
}
