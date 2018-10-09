package com.example.ch05;

import java.util.*;
import java.util.stream.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;

public class IterateStream {
	public static void main(String[] args) throws IOException {
		test05();
	}
	
	public static void test01() {
		//String to Stream
		Stream<String> stream = Stream.of("Java 8", "Lambdas", "In", "Action");
		//Stream map
		stream = stream.map(String::toUpperCase);
		//Stream limit
		stream = stream.limit(2);
		//Stream forEach
		stream.forEach(System.out::println);
		
		//Create empty stream
		Stream.empty();
	}
	
	public static void test02() {
		//Array to IntStream
		IntStream intStream = Arrays.stream(new int[] { 2, 3, 5, 7, 11, 13 });
		//IntStream sum
		int rs = intStream.sum();
		System.out.println(rs);
	}

	public static void test03() {
		//Stream iterate
		Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);
	}

	public static void test04() {
		//Stream generate
		Stream.generate(Math::random).limit(10).forEach(System.out::println);
		//IntStream generate
		IntStream.generate(() -> 1).limit(5).forEach(System.out::println);
	}

	public static void test05() throws IOException {
		long uniqueWords = Files
				.lines(Paths.get("src/main/java/com/example/ch05/data.txt"), Charset.defaultCharset())
				.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();
		System.out.println("There are " + uniqueWords + " unique words in data.txt");
	}
}