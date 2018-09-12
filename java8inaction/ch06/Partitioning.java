package com.example.java8inaction.ch06;

import java.util.*;
import com.example.java8inaction.Dish;
import static com.example.java8inaction.Dish.menu;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class Partitioning {
	public static void main(String[] args) {
		test03();
	}

	public static void test01() {
		Map<Boolean, List<Dish>> rs = menu.stream().collect(partitioningBy(Dish::isVegetarian));
		System.out.println("Dishes partitioned by vegetarian: " + rs);
	}

	public static void test02() {
		Map<Boolean, Map<Dish.Type, List<Dish>>> rs = menu.stream()
				.collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
		System.out.println("Vegetarian Dishes by type: " + rs);
	}

	public static void test03() {
		Object rs = menu.stream().collect(partitioningBy(Dish::isVegetarian,
				collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));
		System.out.println("Most caloric dishes by vegetarian: " + rs);
	}
}
