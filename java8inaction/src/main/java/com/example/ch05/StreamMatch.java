package com.example.ch05;

import com.example.Dish;
import static com.example.Dish.menu;

public class StreamMatch{
	public static void main(String[] args) {
		test03();
	}
	
	public static void test01() {
		//anyMatch
		boolean rs = menu.stream().anyMatch(Dish::isVegetarian);
		System.out.println(rs);
	}
	
	public static void test02() {
		//allMatch
		boolean rs = menu.stream().allMatch(d -> d.getCalories() < 1000);
		System.out.println(rs);
	}
	
	public static void test03() {
		//noneMatch
		boolean rs= menu.stream().noneMatch(d -> d.getCalories() >= 1000);
		System.out.println(rs);
	}
	
	public static void test04() {
		menu.stream()
		.filter(Dish::isVegetarian)
		.findAny()
		.ifPresent(d -> System.out.println(d.getName()));
	}
}
