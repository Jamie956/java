package com.example.java8inaction.ch08;


import java.util.ArrayList;
import java.util.List;


public class ObserverMain2 {
	public static void main(String[] args) {
		test01();
	}

	public static void test01() {
		Feed feedLambda = new Feed();

		feedLambda.registerObserver((String tweet) -> {
			if (tweet != null && tweet.contains("money")) {
				System.out.println("Breaking news in NY! " + tweet);
			}
		});
		feedLambda.registerObserver((String tweet) -> {
			if (tweet != null && tweet.contains("queen")) {
				System.out.println("Yet another news in London... " + tweet);
			}
		});

		feedLambda.notifyObservers("Money money money, give me money!");
	}

	interface Observer {
		void inform(String tweet);
	}

	interface Subject {
		void registerObserver(Observer o);

		void notifyObservers(String tweet);
	}

	static private class Feed implements Subject {
		private final List<Observer> observers = new ArrayList<>();

		public void registerObserver(Observer o) {
			this.observers.add(o);
		}

		public void notifyObservers(String tweet) {
			observers.forEach(o -> o.inform(tweet));
		}
	}
}