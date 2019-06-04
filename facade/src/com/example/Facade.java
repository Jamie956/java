package com.example;

import java.util.*;

public class Facade {
	private final List<Worker> workers;

	public Facade() {
		workers = new ArrayList<>();
		workers.add(new A());
		workers.add(new B());
	}

	public void startNewDay() {
		makeActions(workers, "wake", "work");
	}

	public void endDay() {
		makeActions(workers, "wake", "sleep");
	}

	private static void makeActions(Collection<Worker> workers, String... actions) {
		for (Worker worker : workers) {
			worker.action(actions);
		}
	}
}
