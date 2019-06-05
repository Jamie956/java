package com.example;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamConvert {
	public static void main(String[] args) {
		test02();
	}
	
	public static void test01() {
		// String to List
		List<String> list = Arrays.asList("a", "b", "c");
		//List -> stream
		Stream<String> stream = list.stream();
		stream.forEach(System.out::println);
	}
	
	public static void test02() {
		// String to Stream
		Stream<String> stream = Stream.of("a", "b", "c");
		// Stream -> List
		List<String> streamList = stream.collect(toList());
		streamList.forEach(System.out::println);
	}
}
