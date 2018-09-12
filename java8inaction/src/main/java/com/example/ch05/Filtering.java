package com.example.ch05;

import com.example.Dish;

import java.util.*;
import static java.util.stream.Collectors.toList;

public class Filtering {
	public static void main(String[] args) {
		test04();
	}

	public static void test01() {
		// Filtering with predicate
		List<Dish> vegetarianMenu = Dish.menu.stream().filter(Dish::isVegetarian).collect(toList());
		vegetarianMenu.forEach(System.out::println);
	}

	public static void test02() {
		// Filtering unique elements
		List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
		numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);
	}

	public static void test03() {
		// Truncating a stream
		List<Dish> dishesLimit3 = Dish.menu.stream().filter(d -> d.getCalories() > 300).limit(3).collect(toList());
		dishesLimit3.forEach(System.out::println);
	}

	public static void test04() {
		// Skipping elements
		List<Dish> dishesSkip2 = Dish.menu.stream().filter(d -> d.getCalories() > 300).skip(2).collect(toList());
		dishesSkip2.forEach(System.out::println);
	}
}
