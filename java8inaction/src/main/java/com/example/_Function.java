package com.example;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;


public class _Function {
	public static void main(String[] args) {
		test03();
	}

	public static void test01() {
		UnaryOperator<String> a = (String text) -> "Say: " + text;
		UnaryOperator<String> b = (String text) -> text.replaceAll("labda", "lambda");
		// and then
		Function<String, String> pipeline = a.andThen(b);
		System.out.println(pipeline.apply("Aren't labdas really sexy?!!"));
	}
	
	public static void test02() {
		Cat strategy = (String word) -> System.out.println(word);
		strategy.say("hi");
	}
	
	public static void test03() {
	    Consumer<String> consumer = (String x) -> System.out.println(x);
	    consumer.accept("w");
	}
	
	interface Cat {
		public void say(String word);
	}
}
