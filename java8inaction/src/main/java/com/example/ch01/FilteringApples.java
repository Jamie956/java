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
		// Predicate<T>定义boolean方法
		Predicate<Apple> p = FilteringApples::isGreenApple;
		// 调用定义的方法，test传入参数
		boolean rs = p.test(new Apple(80, "red"));
		System.out.println(rs);
	}

	public static void test01() {
		// 两个冒号调用方法
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
		// 使用箭头函数
		Predicate<Apple> p = (Apple a) -> "green".equals(a.getColor());
		List<Apple> rs = new ArrayList<>();
		for (Apple a : as) {
			if (p.test(a)) {
				rs.add(a);
			}
		}
		System.out.println(rs);
	}

	public static void test03() {
		// 多条件
		Predicate<Apple> p = (Apple a) -> a.getWeight() < 80 || "brown".equals(a.getColor());
		List<Apple> rs = new ArrayList<>();
		for (Apple a : as) {
			if (p.test(a)) {
				rs.add(a);
			}
		}
		System.out.println(rs);
	}

	public static boolean isGreenApple(Apple apple) {
		return "green".equals(apple.getColor());
	}
}
