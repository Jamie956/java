package com.example.ch05;

import com.example.Dish;
import static com.example.Dish.menu;
import java.util.*;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class FilterStream {
	public static void main(String[] args) {
		test04();
	}
	
	public static void test00() {
		//List -> Stream
		Stream<Dish> stream = menu.stream();
		//Stream -> List
		List<Dish> list = stream.collect(toList());
		//List forEach
		list.forEach(System.out::println);
	}
	
	public static void test01() {
		Stream<Dish> stream = menu.stream();
		//Stream filter
		stream = stream.filter(Dish::isVegetarian);
		stream.forEach(System.out::println);
	}

	public static void test02() {
		List<Integer> list = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
		Stream<Integer> stream = list.stream();
		// distinct: 去重
		stream = stream.distinct();
		stream.forEach(System.out::println);
	}

	public static void test03() {
		Stream<Dish> stream = menu.stream();
		//skip: 跳过开头的元素
		stream = stream.skip(2);
		stream.forEach(System.out::println);
	}
	
	public static void test04() {
		Stream<Dish> stream = menu.stream();
		// sorted: 排序
		stream = stream.sorted(comparing(Dish::getCalories));
		stream.forEach(System.out::println);
	}
}
