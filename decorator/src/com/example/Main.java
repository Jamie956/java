package com.example;

public class Main {
	public static void main(String[] args) {
		// simple troll
		Troll troll = new SimpleTroll();
		troll.attack(); // The troll tries to grab you!
		troll.fleeBattle(); // The troll shrieks in horror and runs away!

		// change the behavior of the simple troll by adding a decorator
		Troll clubbedTroll = new ClubbedTroll(troll);
		clubbedTroll.attack(); // The troll tries to grab you! The troll swings at you with a club!
		clubbedTroll.fleeBattle(); // The troll shrieks in horror and runs away!

	}
}
