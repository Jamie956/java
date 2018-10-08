package com.example.ch05;

import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;

public class BuildingStreams {
	public static void main(String[] args) throws IOException {
		test01();
	}

	public static void test01() {
		Stream<String> stream = Stream.of("Java 8", "Lambdas", "In", "Action");
		stream.map(String::toUpperCase).forEach(System.out::println);
	}

	public static void test02() {
		Stream<String> stream = Stream.empty();
	}

	public static void test03() {
		int[] numbers = { 2, 3, 5, 7, 11, 13 };
		int rs = Arrays.stream(numbers).sum();
		System.out.println(rs);
	}

	public static void test04() {
		Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);
	}

	public static void test05() {
		Stream.iterate(new int[] { 0, 1 }, t -> new int[] { t[1], t[0] + t[1] }).limit(10)
				.forEach(t -> System.out.println("(" + t[0] + ", " + t[1] + ")"));
	}

	public static void test06() {
		Stream.iterate(new int[] { 0, 1 }, t -> new int[] { t[1], t[0] + t[1] }).limit(10).map(t -> t[0])
				.forEach(System.out::println);
	}

	public static void test07() {
		Stream.generate(Math::random).limit(10).forEach(System.out::println);
	}

	public static void test08() {
		IntStream.generate(() -> 1).limit(5).forEach(System.out::println);
	}

	public static void test09() {
		IntStream.generate(new IntSupplier() {
			public int getAsInt() {
				return 2;
			}
		}).limit(5).forEach(System.out::println);
	}

	public static void test10() {
		IntSupplier fib = new IntSupplier() {
			private int previous = 0;
			private int current = 1;

			public int getAsInt() {
				int nextValue = this.previous + this.current;
				this.previous = this.current;
				this.current = nextValue;
				return this.previous;
			}
		};
		IntStream.generate(fib).limit(10).forEach(System.out::println);
	}

	public static void test11() throws IOException {
		long uniqueWords = Files
				.lines(Paths.get("src/main/java/com/example/java8inaction/ch05/data.txt"), Charset.defaultCharset())
				.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();

		System.out.println("There are " + uniqueWords + " unique words in data.txt");
	}
}