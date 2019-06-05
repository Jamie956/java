package com.example;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Stream_List {
	private static List<String> list = Arrays.asList("a", "b", "c");
	
	public static void main(String[] args) {
		test02();
	}
	
	public static void test01() {
		//List -> stream
		Stream<String> stream = list.stream();
		stream.forEach(System.out::println);
	}
	
	public static void test02() {
		Stream<String> stream = list.stream();
		// Stream -> List
		List<String> streamList = stream.collect(toList());
		streamList.forEach(System.out::println);
	}
}
