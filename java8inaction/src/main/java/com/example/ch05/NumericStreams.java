package com.example.ch05;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import static com.example.Dish.menu;
import com.example.Dish;

public class NumericStreams {
	public static void main(String[] args) {
		test04();
	}

	public static void test01() {
		int[] array = new int[] {3, 4, 5, 1, 2};
		//Array -> IntStream
		IntStream stream = Arrays.stream(array);
		stream.forEach(System.out::println);
	}

	public static void test02() {
		Stream<Dish> stream = menu.stream();
		IntStream intStream = stream.mapToInt(Dish::getCalories);
		//intStream sum
		int i = intStream.sum();
		System.out.println(i);
	}

	public static void test03() {
		Stream<Dish> stream = menu.stream();
		IntStream intStream = stream.mapToInt(Dish::getCalories);
		//intStream max
		OptionalInt maxOp = intStream.max();
		
		int max;
		if (maxOp.isPresent()) {
			max = maxOp.getAsInt();
		} else {
			max = 1;
		}
		System.out.println(max);
	}

	public static void test04() {
		// IntStream rangeClosed
		IntStream intStream = IntStream.rangeClosed(1, 100);
		intStream.forEach(System.out::println);
	}
}