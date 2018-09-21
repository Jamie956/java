package com.example;

public class App {
	public static void main(String[] args) {
		System.out.println("Elf Kingdom");
		CreateKingdom kd = new CreateKingdom(FactoryMaker.create(KingdomType.ELF));
		System.out.println(kd.getArmy().getDescription());
		System.out.println(kd.getCastle().getDescription());
		System.out.println(kd.getKing().getDescription());

		System.out.println("Orc Kingdom");
		CreateKingdom kd2 = new CreateKingdom(FactoryMaker.create(KingdomType.ORC));
		System.out.println(kd2.getArmy().getDescription());
		System.out.println(kd2.getCastle().getDescription());
		System.out.println(kd2.getKing().getDescription());
	}
}