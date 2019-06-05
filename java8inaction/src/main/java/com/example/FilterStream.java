package com.example;

import java.util.*;
import java.util.stream.Stream;

public class FilterStream {
	public static void main(String[] args) {
		test04();
	}
	
	private static Stream<String> stream = Arrays.asList("a", "b", "c", "a").stream();

	public static void test01() {
		//Stream filter
		stream = stream.filter((x) -> x == "a" );
		stream.forEach(System.out::println);
	}

	public static void test02() {
		// Stream distinct: 去重
		stream = stream.distinct();
		stream.forEach(System.out::println);
	}

	public static void test03() {
		//skip: 跳过前两个元素
		stream = stream.skip(2);
		stream.forEach(System.out::println);
	}
	
	public static void test04() {
		// sorted
		stream = stream.sorted((a, b) -> a.compareTo(b));
		stream.forEach(System.out::println);
	}
}