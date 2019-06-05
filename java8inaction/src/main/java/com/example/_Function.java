package com.example;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class _Function {
	public static void main(String[] args) {
		UnaryOperator<String> a = (String text) -> "Say: " + text;
		UnaryOperator<String> b = (String text) -> text.replaceAll("labda", "lambda");
		// and then
		Function<String, String> pipeline = a.andThen(b);
		System.out.println(pipeline.apply("Aren't labdas really sexy?!!"));
	}

}
