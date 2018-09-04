package com.example.java8inaction.ch01;

import java.util.*;
import java.util.function.Predicate;

public class FilteringApples {
	public static void main(String[] args) {
		test00();
	}
	
	public static List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));
	
	public static void test00() {
		//Predicate<T>定义boolean方法
		Predicate<Apple> myFun = FilteringApples::isGreenApple;
		//调用定义的方法，test传入参数
		boolean rs = myFun.test(new Apple(80, "red"));
		System.out.println(rs);
	}
	
	public static void test01() {
		//两个冒号调用方法
		Predicate<Apple> myFun = FilteringApples::isGreenApple;
		List<Apple> rs = new ArrayList<>();
		for (Apple a : inventory) {
			if (myFun.test(a)) {
				rs.add(a);
			}
		}
		System.out.println(rs);
	}

	public static void test02() {
		//使用箭头函数
		Predicate<Apple> myFun = (Apple a) -> "green".equals(a.getColor());
		List<Apple> rs = new ArrayList<>();
		for (Apple a : inventory) {
			if (myFun.test(a)) {
				rs.add(a);
			}
		}
		System.out.println(rs);
	}

	public static void test03() {
		//多条件
		Predicate<Apple> myFun = (Apple a) -> a.getWeight() < 80 || "brown".equals(a.getColor());
		List<Apple> rs = new ArrayList<>();
		for (Apple a : inventory) {
			if (myFun.test(a)) {
				rs.add(a);
			}
		}
		System.out.println(rs);
	}


	
	public static boolean isGreenApple(Apple apple) {
		return "green".equals(apple.getColor());
	}

	public static class Apple {
		private int weight = 0;
		private String color = "";

		public Apple(int weight, String color) {
			this.weight = weight;
			this.color = color;
		}

		public Integer getWeight() {
			return weight;
		}

		public void setWeight(Integer weight) {
			this.weight = weight;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}

		public String toString() {
			return "Apple{" + "color='" + color + '\'' + ", weight=" + weight + '}';
		}
	}
}
