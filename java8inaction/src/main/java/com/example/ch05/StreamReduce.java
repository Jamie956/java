package com.example.ch05;

import com.example.Dish;
import java.util.*;

public class StreamReduce {
	public static void main(String[] args) {
		test05();
	}

	public static List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);

	public static void test01() {
		int sum = numbers.stream().reduce(0, (a, b) -> a + b);
		System.out.println(sum);
	}

	public static void test02() {
		int sum2 = numbers.stream().reduce(0, Integer::sum);
		System.out.println(sum2);
	}

	public static void test03() {
		int max = numbers.stream().reduce(0, (a, b) -> Integer.max(a, b));
		System.out.println(max);
	}

	public static void test04() {
		Optional<Integer> min = numbers.stream().reduce(Integer::min);
		min.ifPresent(System.out::println);
	}

	public static void test05() {
		int calories = Dish.menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
		System.out.println("Number of calories:" + calories);
	}
}
