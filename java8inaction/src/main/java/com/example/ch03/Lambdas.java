package com.example.ch03;

import java.util.*;

import com.example.Apple;

public class Lambdas {
	public static void main(String[] args) {
		test03();
	}
	
	public static List<Apple> inventory = Arrays.asList(new Apple(80,"green"), new Apple(155, "green"), new Apple(120, "red"));
	
	public static void test01() {
		Runnable r = () -> System.out.println("Hello!");
		r.run();	
	}
	
	public static void test02() {
		ApplePredicate p = (Apple a) -> "green".equals(a.getColor());
		List<Apple> rs = new ArrayList<>();
		for(Apple apple : inventory){
			if(p.test(apple)){
				rs.add(apple);
			}
		}
		System.out.println(rs);
	}
	
	//数组排序
	public static void test03() {
		Comparator<Apple> c = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
		inventory.sort(c);
		System.out.println(inventory);	
	}

	interface ApplePredicate{
		public boolean test(Apple a);
	}
}
