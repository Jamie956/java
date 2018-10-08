package com.example.ch01;

import java.util.*;
import java.util.function.Predicate;

import com.example.Apple;

public class FilteringApples {
	public static void main(String[] args) {
		test00();
	}

	public static List<Apple> as = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"),
			new Apple(120, "red"));

	public static void test00() {
		// 获取boolean方法
		Predicate<Apple> p = FilteringApples::isGreenApple;
		boolean rs = p.test(new Apple(80, "red"));
		System.out.println(rs);
	}

	public static void test01() {
		Predicate<Apple> p = FilteringApples::isGreenApple;
		List<Apple> rs = new ArrayList<>();
		for (Apple a : as) {
			if (p.test(a)) {
				rs.add(a);
			}
		}
		System.out.println(rs);
	}

	public static void test02() {
		Predicate<Apple> p = (Apple a) -> "green".equals(a.getColor());
		p.test(new Apple(80, "red"));
	}

	public static void test03() {
		Predicate<Apple> p = (Apple a) -> a.getWeight() < 80 || "brown".equals(a.getColor());
		p.test(new Apple(80, "red"));
	}

	public static boolean isGreenApple(Apple apple) {
		return "green".equals(apple.getColor());
	}

}
