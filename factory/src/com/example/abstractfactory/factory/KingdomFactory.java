package com.example.abstractfactory.factory;

import com.example.abstractfactory.entity.*;

public interface KingdomFactory {
	Castle createCastle();

	King createKing();

	Army createArmy();
}
