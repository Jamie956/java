package com;

import java.util.ArrayList;
import java.util.List;

public class LambdaInvo {
	public static void main(String[] args) {
		List<Observer> observers = new ArrayList<>();

		observers.add((String tweet) -> {
			System.out.println("a "+tweet);
		});
		
		observers.add((String tweet) -> {
			System.out.println("b "+tweet);
		});
		
		observers.forEach(o -> o.inform("tweet"));
	}

	interface Observer {
		void inform(String tweet);
	}
}