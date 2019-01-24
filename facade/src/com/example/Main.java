package com.example;

public class Main {
	public static void main(String[] args) {
		DwarvenGoldmineFacade facade = new DwarvenGoldmineFacade();
		facade.startNewDay();
		// Dwarf gold digger wakes up.
		// Dwarf gold digger goes to the mine.
		// Dwarf cart operator wakes up.
		// Dwarf cart operator goes to the mine.
		// Dwarven tunnel digger wakes up.
		// Dwarven tunnel digger goes to the mine.
		facade.digOutGold();
		// Dwarf gold digger digs for gold.
		// Dwarf cart operator moves gold chunks out of the mine.
		// Dwarven tunnel digger creates another promising tunnel.
		facade.endDay();
		// Dwarf gold digger goes home.
		// Dwarf gold digger goes to sleep.
		// Dwarf cart operator goes home.
		// Dwarf cart operator goes to sleep.
		// Dwarven tunnel digger goes home.
		// Dwarven tunnel digger goes to sleep.

	}
}
