package com.example.ch05;

import com.example.Dish;
import static com.example.Dish.menu;
import java.util.*;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class FilterStream {
	public static void main(String[] args) {
		test02();
	}
	
	public static void test01() {
		//List -> Stream
		Stream<Dish> stream = menu.stream();
		//Stream filter
		stream = stream.filter(Dish::isVegetarian);
		//Stream -> List
		List<Dish> list = stream.collect(toList());
		//List forEach
		list.forEach(System.out::println);
	}

	public static void test02() {
		// distinct: 去重
//		Arrays.asList(1, 2, 1, 3, 3, 2, 4)
//		.stream()
//		.filter(i -> i % 2 == 0)
//		.distinct()
//		.forEach(System.out::println);
		
		// distinct: 去重
		Arrays.asList(1, 2, 1, 3, 3, 2, 4)
		.stream()
		.filter(i -> i % 2 == 0)
		.distinct()
		.forEach(System.out::println);
	}

	public static void test03() {
		// limit: count limit
		menu.stream()
		.filter(d -> d.getCalories() > 300)
		.limit(3)
		.collect(toList())
		.forEach(System.out::println);
	}

	public static void test04() {
		// skip: remove it from begin
		menu.stream()
		.filter(d -> d.getCalories() > 300)
		.skip(2)
		.collect(toList())
		.forEach(System.out::println);
	}
	
	public static void test05() {
		// sorted: 排序
		menu.stream()
		.filter(d -> d.getCalories() < 400)
		.sorted(comparing(Dish::getCalories))
		.map(Dish::getName)
		.collect(toList())
		.forEach(System.out::println);
	}
}
