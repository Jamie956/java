package com.example.java8inaction.ch05;

import java.util.*;

import com.example.java8inaction.Dish;

public class Finding{
	public static void main(String[] args) {
		test04();
	}
	
	public static void test01() {
		boolean rs = Dish.menu.stream().anyMatch(Dish::isVegetarian);
		System.out.println(rs);
	}
	
	public static void test02() {
		boolean rs = Dish.menu.stream().allMatch(d -> d.getCalories() < 1000);
		System.out.println(rs);
	}
	
	public static void test03() {
		boolean rs= Dish.menu.stream().noneMatch(d -> d.getCalories() >= 1000);
		System.out.println(rs);
	}
	
	public static void test04() {
		Optional<Dish> dish = Dish.menu.stream().filter(Dish::isVegetarian).findAny();
        dish.ifPresent(d -> System.out.println(d.getName()));
	}
}
