package com;

import java.util.function.Predicate;

public class Filter {
	public static void main(String[] args) {
		test02();
	}

	private static boolean isGreenApple(Color color) {
		return "green".equals(color.getName());
	}
	
	public static void test01() {
		Predicate<Color> predicate = Filter::isGreenApple;
		
		System.out.println(predicate.test(new Color("red")));
		System.out.println(predicate.test(new Color("green")));
	}

	public static void test02() {
		Predicate<Color> predicate = (Color color) -> "green".equals(color.getName());
		
		System.out.println(predicate.test(new Color("green")));
		System.out.println(predicate.test(new Color("red")));
	}

}
