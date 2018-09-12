package com.example.ch06;

import java.util.*;
import java.util.function.*;
import static java.util.stream.Collectors.*;
import com.example.Dish;
import static com.example.Dish.menu;

public class Summarizing {
	public static void main(String[] args) {
		test08();
	}

	public static void test01() {
		Long rs = menu.stream().collect(counting());
		System.out.println("Nr. of dishes: " + rs);
	}

	public static void test02() {
		Dish rs = menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)).get();
		System.out.println("The most caloric dish is: " + rs);
	}

	public static void test03() {
		Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
		BinaryOperator<Dish> moreCaloricOf = BinaryOperator.maxBy(dishCaloriesComparator);
		Dish rs = menu.stream().collect(reducing(moreCaloricOf)).get();
		System.out.println("The most caloric dish is: " + rs);
	}

	public static void test04() {
		int rs = menu.stream().collect(summingInt(Dish::getCalories));
		System.out.println("Total calories in menu: " + rs);
	}

	public static void test05() {
		Double rs = menu.stream().collect(averagingInt(Dish::getCalories));
		System.out.println("Average calories in menu: " + rs);
	}

	public static void test06() {
		IntSummaryStatistics rs = menu.stream().collect(summarizingInt(Dish::getCalories));
		System.out.println("Menu statistics: " + rs);
	}

	public static void test07() {
		String rs = menu.stream().map(Dish::getName).collect(joining());
		System.out.println("Short menu: " + rs);
	}

	public static void test08() {
		String rs = menu.stream().map(Dish::getName).collect(joining(", "));
		System.out.println("Short menu comma separated: " + rs);
	}

}