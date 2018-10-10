package com.example.ch06;

import java.util.*;

import com.example.Dish;
import static com.example.Dish.menu;

import static java.util.stream.Collectors.*;

public class Grouping {
	enum CaloricLevel {
		DIET, NORMAL, FAT
	};

	public static void main(String[] args) {
		test01();
	}

	public static void test01() {
		// collect groupingBy
		Map<Dish.Type, List<Dish>> rs = menu.stream().collect(groupingBy(Dish::getType));
		System.out.println(rs);
	}

	public static void test02() {
		Map<Dish.Type, List<Integer>> rs = menu.stream()
				.collect(groupingBy(Dish::getType, mapping(Dish::getCalories, toList())));
		System.out.println(rs);
	}

	public static void test05() {
		Map<Dish.Type, Long> rs = menu.stream().collect(groupingBy(Dish::getType, counting()));
		System.out.println(rs);
	}

	public static void test06() {
		Map<Dish.Type, Optional<Dish>> rs = menu.stream().collect(groupingBy(Dish::getType,
				reducing((Dish d1, Dish d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)));
		System.out.println("Most caloric dishes by type: " + rs);
	}

	public static void test07() {
		Map<Dish.Type, Dish> rs = menu.stream()
				.collect(groupingBy(Dish::getType, collectingAndThen(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2), Optional::get)));
		System.out.println("Most caloric dishes by type: " + rs);
	}

	public static void test08() {
		Map<Dish.Type, Integer> rs = menu.stream()
				.collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
		System.out.println("Sum calories by type: " + rs);
	}

}
