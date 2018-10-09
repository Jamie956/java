package com.example.ch05;

import java.util.*;
import static java.util.stream.Collectors.toList;

public class StreamMap {
	public static void main(String[] args) {
		test02();
	}

	public static void test01() {
		Arrays.asList("JS", "JAVA").stream()
		.map(String::length)
		.collect(toList())
		.forEach(System.out::println);;
	}

	public static void test02() {
		Arrays.asList("JS", "JAVA").stream()
		.flatMap((String line) -> Arrays.stream(line.split("")))
		.forEach(System.out::println);
	}

}


