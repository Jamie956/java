package com.example.ch05;

import com.example.Dish;

import java.util.*;
import static java.util.stream.Collectors.toList;

public class FilterStream {
	public static void main(String[] args) {
		test05();
	}

	public static void test01() {
		// Filtering with predicate
		Dish.menu.stream()
		.filter(Dish::isVegetarian)
		.collect(toList())
		.forEach(System.out::println);
	}

	public static void test02() {
		// Filtering unique elements
		Arrays.asList(1, 2, 1, 3, 3, 2, 4).stream()
		.filter(i -> i % 2 == 0)
		.distinct()
		.forEach(System.out::println);
	}

	public static void test03() {
		// Truncating a stream
		Dish.menu.stream()
		.filter(d -> d.getCalories() > 300)
		.limit(3)
		.collect(toList())
		.forEach(System.out::println);
	}

	public static void test04() {
		// Skipping elements from begin
		Dish.menu.stream()
		.filter(d -> d.getCalories() > 300)
		.skip(2)
		.collect(toList())
		.forEach(System.out::println);
	}
	
	public static void test05() {
		Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8).stream().filter(n -> {
			System.out.println("filtering " + n);
			return n % 2 == 0;
		}).map(n -> {
			System.out.println("mapping " + n);
			return n * n;
		}).limit(2).collect(toList());
	}
}
