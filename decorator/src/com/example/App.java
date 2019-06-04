package com.example;

public class App {
	public static void main(String[] args) {
		Troll simpleTroll = new SimpleTroll();
		simpleTroll.attack();
		simpleTroll.fleeBattle();
		System.out.println(simpleTroll.getAttackPower());
		
		System.out.println("===");
		
		Troll clubbedTroll = new ClubbedTroll(simpleTroll);
		clubbedTroll.attack();
		clubbedTroll.fleeBattle();
		System.out.println(clubbedTroll.getAttackPower());
	}
}
