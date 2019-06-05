package com;

import java.util.*;

import static java.util.Comparator.comparing;

public class Sorting {
	public static void main(String[] args) {
		test01();
	}

	public static List<Color> colors = Arrays.asList(new Color("yellow"), new Color("green"), new Color("red"));

	public static void test01() {
		colors.sort(new Comparator<Color>() {
			public int compare(Color a, Color b) {
				return a.getName().compareTo(b.getName());
			}
		});
		System.out.println(colors);
	}

	public static void test02() {
		colors.sort((a, b) -> a.getName().compareTo(b.getName()));
		System.out.println(colors);
	}

	public static void test03() {
		colors.sort(comparing(Color::getName));
		System.out.println(colors);
	}

}
