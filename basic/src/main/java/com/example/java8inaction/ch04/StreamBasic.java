package com.example.java8inaction.ch04;

import java.util.*;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class StreamBasic {
	public static void main(String[] args) {
		test02();
	}

	// java7写法
	public static void test01() {
		List<Dish> lowCaloricDishes = new ArrayList<>();
		// 获取小于400卡路里的菜
		for (Dish d : Dish.menu) {
			if (d.getCalories() < 400) {
				lowCaloricDishes.add(d);
			}
		}
		// 按卡路里排序
		Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
			public int compare(Dish d1, Dish d2) {
				return Integer.compare(d1.getCalories(), d2.getCalories());
			}
		});
		// 获取菜名
		List<String> lowCaloricDishesName = new ArrayList<>();
		for (Dish d : lowCaloricDishes) {
			lowCaloricDishesName.add(d.getName());
		}

		lowCaloricDishesName.forEach(System.out::println);
	}

	// Java 8写法
	public static void test02() {
		List<String> rs = Dish.menu.stream().filter(d -> d.getCalories() < 400).sorted(comparing(Dish::getCalories))
				.map(Dish::getName).collect(toList());
		rs.forEach(System.out::println);
	}
}
