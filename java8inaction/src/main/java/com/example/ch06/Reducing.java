package com.example.ch06;

import static java.util.stream.Collectors.*;
import com.example.Dish;
import static com.example.Dish.menu;

public class Reducing {
	public static void main(String[] args) {
		test04();
	}

	public static void test01() {
		int rs = menu.stream().collect(reducing(0, Dish::getCalories, (Integer i, Integer j) -> i + j));
		System.out.println(rs);
	}

	public static void test02() {
		int rs = menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
		System.out.println(rs);
	}

	public static void test03() {
		int rs = menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
		System.out.println(rs);
	}

	public static void test04() {
		int rs = menu.stream().mapToInt(Dish::getCalories).sum();
		System.out.println(rs);
	}
}