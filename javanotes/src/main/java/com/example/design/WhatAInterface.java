package com.example.design;

public class WhatAInterface {
	public static void main(String[] args) {
		Zookeeper zookeeper = new Zookeeper();
		Rabbit rabbit = new Rabbit();
		zookeeper.playWith(rabbit);
		zookeeper.tellLeft();
		zookeeper.tellRight();
		zookeeper.tellLeftAndRight();
	}
}

class Zookeeper {
	private Creature creature;

	public void playWith(Creature creature) {
		this.creature = creature;
	}

	public void tellLeft() {
		creature.left();
	}

	public void tellRight() {
		creature.right();
	}

	public void tellLeftAndRight() {
		creature.left();
		creature.right();
	}

}

interface Creature {
	public void left();

	public void right();
}

class Rabbit implements Creature {

	public void left() {
		System.out.println("left left");
	}

	public void right() {
		System.out.println("right right");
	}

}
