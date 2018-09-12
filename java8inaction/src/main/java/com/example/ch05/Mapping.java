package com.example.ch05;

import java.util.*;
import com.example.Dish;
import static java.util.stream.Collectors.toList;

public class Mapping {
	public static void main(String[] args) {
		test04();
	}

	public static List<String> words = Arrays.asList("Hello", "World");

	public static void test01() {
		List<String> dishNames = Dish.menu.stream().map(Dish::getName).collect(toList());
		System.out.println(dishNames);
	}

	public static void test02() {
		List<Integer> wordLengths = words.stream().map(String::length).collect(toList());
		System.out.println(wordLengths);
	}

	public static void test03() {
		words.stream().flatMap((String line) -> Arrays.stream(line.split(""))).distinct().forEach(System.out::println);
	}

	public static void test04() {
		List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> numbers2 = Arrays.asList(6, 7, 8);
		List<int[]> pairs = numbers1.stream()
				.flatMap((Integer i) -> numbers2.stream().map((Integer j) -> new int[] { i, j }))
				.filter(pair -> (pair[0] + pair[1]) % 3 == 0).collect(toList());
		pairs.forEach(pair -> System.out.println("(" + pair[0] + ", " + pair[1] + ")"));
	}

}