package com.example.ch02;

import java.util.*;

import com.example.Apple;

public class FilteringApples {
	public static void main(String[] args) {
		test01();
	}

	public static List<Apple> as = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"),
			new Apple(120, "red"));

	public static void test01() {
		ApplePredicate p = new AppleColorPredicate();
		List<Apple> rs = new ArrayList<>();
		for (Apple a : as) {
			if (p.test(a)) {
				rs.add(a);
			}
		}
		System.out.println(rs);
	}

	public static void test02() {
		ApplePredicate p = new AppleWeightPredicate();
		List<Apple> rs = new ArrayList<>();
		for (Apple a : as) {
			if (p.test(a)) {
				rs.add(a);
			}
		}
		System.out.println(rs);
	}

	public static void test03() {
		ApplePredicate p = new ApplePredicate() {
			public boolean test(Apple a) {
				return a.getColor().equals("red");
			}
		};
		List<Apple> rs = new ArrayList<>();
		for (Apple a : as) {
			if (p.test(a)) {
				rs.add(a);
			}
		}
		System.out.println(rs);
	}



	interface ApplePredicate {
		public boolean test(Apple a);
	}

	static class AppleWeightPredicate implements ApplePredicate {
		public boolean test(Apple apple) {
			return apple.getWeight() > 150;
		}
	}

	static class AppleColorPredicate implements ApplePredicate {
		public boolean test(Apple apple) {
			return "green".equals(apple.getColor());
		}
	}
}