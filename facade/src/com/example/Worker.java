package com.example;

public abstract class Worker {
	public void goToSleep() {
		System.out.println(name() + " goes to sleep.");
	}

	public void wakeUp() {
		System.out.println(name() + " wakes up.");
	}

	private void action(String action) {
		switch (action) {
		case "sleep":
			goToSleep();
			break;
		case "wake":
			wakeUp();
			break;
		case "work":
			work();
			break;
		default:
			System.out.println("Undefined action");
			break;
		}
	}

	public void action(String... actions) {
		for (String action : actions) {
			action(action);
		}
	}

	public abstract void work();

	public abstract String name();

}