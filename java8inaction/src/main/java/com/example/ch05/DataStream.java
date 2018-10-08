package com.example.ch05;

import com.example.Dish;

import java.util.*;
import static java.util.stream.Collectors.toList;

public class DataStream {
	public static void main(String[] args) {
		test04();
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
}
