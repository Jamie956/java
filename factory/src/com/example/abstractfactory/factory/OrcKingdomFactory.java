package com.example.abstractfactory.factory;

import com.example.abstractfactory.entity.*;

public class OrcKingdomFactory implements KingdomFactory {
	public Castle createCastle() {
		return new OrcCastle();
	}

	public King createKing() {
		return new OrcKing();
	}

	public Army createArmy() {
		return new OrcArmy();
	}
}
