package com.example.ch03;

import java.util.*;
import com.example.Apple;
import static java.util.Comparator.comparing;

public class Sorting {
	public static void main(String[] args) {
		test04();
	}

	public static List<Apple> as = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"),
			new Apple(120, "red"));

	public static void test01() {
		as.sort(new AppleComparator());
		System.out.println(as);
	}

	public static void test02() {
		as.sort(new Comparator<Apple>() {
			public int compare(Apple a1, Apple a2) {
				return a1.getWeight().compareTo(a2.getWeight());
			}
		});
		System.out.println(as);
	}

	public static void test03() {
		as.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
		System.out.println(as);
	}

	public static void test04() {
		as.sort(comparing(Apple::getWeight));
		System.out.println(as);
	}

	static class AppleComparator implements Comparator<Apple> {
		public int compare(Apple a1, Apple a2) {
			return a1.getWeight().compareTo(a2.getWeight());
		}
	}
	
}
