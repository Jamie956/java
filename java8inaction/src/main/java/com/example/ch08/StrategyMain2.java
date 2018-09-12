package com.example.ch08;

public class StrategyMain2 {
	public static void main(String[] args) {
		test01();
	}

	public static void test01() {
		// with lambdas
		Validator v3 = new Validator((String s) -> s.matches("\\d+"));
		System.out.println(v3.validate("aaaa"));

		Validator v4 = new Validator((String s) -> s.matches("[a-z]+"));
		System.out.println(v4.validate("bbbb"));
	}

	interface ValidationStrategy {
		public boolean execute(String s);
	}

	static private class Validator {
		private final ValidationStrategy strategy;

		public Validator(ValidationStrategy v) {
			this.strategy = v;
		}

		public boolean validate(String s) {
			return strategy.execute(s);
		}
	}
}
