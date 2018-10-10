package com.example.ch08;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Process {
	public static void main(String[] args) {
		test01();
	}

	public static void test01() {
		UnaryOperator<String> a = (String text) -> "From Raoul, Mario and Alan: " + text;
		UnaryOperator<String> b = (String text) -> text.replaceAll("labda", "lambda");
		Function<String, String> pipeline = a.andThen(b);
		String rs = pipeline.apply("Aren't labdas really sexy?!!");
		System.out.println(rs);
	}

}
