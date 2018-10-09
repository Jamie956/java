package com.example.ch05;

import com.example.Dish;
import static com.example.Dish.menu;
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
		int sum = numbers.stream().reduce(0, Integer::sum);
		System.out.println(sum);
	}

	public static void test04() {
		numbers.stream().reduce(Integer::min).ifPresent(System.out::println);
	}

	public static void test05() {
		int i = menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
		System.out.println(i);
	}
}
