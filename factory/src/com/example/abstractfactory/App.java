package com.example.abstractfactory;

import com.example.abstractfactory.entity.*;
import com.example.abstractfactory.factory.ElfKingdomFactory;

public class App {
	public static void main(String[] args) {
		test01();
	}

	public static void test01() {
		ElfKingdomFactory elfKingdomFactory = new ElfKingdomFactory();
		Army elfArmy = elfKingdomFactory.createArmy();
		Castle elfCastle = elfKingdomFactory.createCastle();
		King elfKing = elfKingdomFactory.createKing();

		System.out.println(elfArmy.getDescription());
		System.out.println(elfCastle.getDescription());
		System.out.println(elfKing.getDescription());
	}

	public static void test02() {
		CreateKingdom kd = new CreateKingdom(FactoryMaker.create(KingdomType.ELF));
		
		System.out.println(kd.getArmy().getDescription());
		System.out.println(kd.getCastle().getDescription());
		System.out.println(kd.getKing().getDescription());
	}
}