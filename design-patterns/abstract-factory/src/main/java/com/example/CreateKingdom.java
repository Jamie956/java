package com.example;

import com.example.entity.Army;
import com.example.entity.Castle;
import com.example.entity.King;
import com.example.factory.KingdomFactory;

public class CreateKingdom {
	private King king;
	private Castle castle;
	private Army army;

	CreateKingdom(final KingdomFactory factory) {
		setKing(factory.createKing());
		setCastle(factory.createCastle());
		setArmy(factory.createArmy());
	}

	King getKing(final KingdomFactory factory) {
		return factory.createKing();
	}

	public King getKing() {
		return king;
	}

	private void setKing(final King king) {
		this.king = king;
	}

	Castle getCastle(final KingdomFactory factory) {
		return factory.createCastle();
	}

	public Castle getCastle() {
		return castle;
	}

	private void setCastle(final Castle castle) {
		this.castle = castle;
	}

	Army getArmy(final KingdomFactory factory) {
		return factory.createArmy();
	}

	public Army getArmy() {
		return army;
	}

	private void setArmy(final Army army) {
		this.army = army;
	}
}
