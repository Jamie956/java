package com.example.java8inaction.ch05;

import java.util.*;
import static java.util.stream.Collectors.toList;

public class Laziness {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

		numbers.stream().filter(n -> {
			System.out.println("filtering " + n);
			return n % 2 == 0;
		}).map(n -> {
			System.out.println("mapping " + n);
			return n * n;
		}).limit(2).collect(toList());

	}
}