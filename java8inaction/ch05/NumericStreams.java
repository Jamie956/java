package com.example.java8inaction.ch05;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.example.java8inaction.Dish;

public class NumericStreams {
	public static void main(String[] args) {
		test05();
	}

	public static void test01() {
		List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);
		Arrays.stream(numbers.toArray()).forEach(System.out::println);
	}

	public static void test02() {
		int calories = Dish.menu.stream().mapToInt(Dish::getCalories).sum();
		System.out.println("Number of calories:" + calories);
	}

	public static void test03() {
		// max and OptionalInt
		OptionalInt maxCalories = Dish.menu.stream().mapToInt(Dish::getCalories).max();

		int max;
		if (maxCalories.isPresent()) {
			max = maxCalories.getAsInt();
		} else {
			// we can choose a default value
			max = 1;
		}
		System.out.println(max);
	}

	public static void test04() {
		// numeric ranges
		IntStream evenNumbers = IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0);
		System.out.println(evenNumbers.count());
	}

	public static void test05() {
		Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed()
				.flatMap(a -> IntStream.rangeClosed(a, 100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).boxed()
						.map(b -> new int[] { a, b, (int) Math.sqrt(a * a + b * b) }));

		pythagoreanTriples.forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
	}
}