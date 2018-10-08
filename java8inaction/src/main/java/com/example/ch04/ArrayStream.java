package com.example.ch04;

import java.util.*;

public class ArrayStream {
	public static void main(String[] args) {
		test01();
	}
	public static void test01() {
        Arrays.asList("Java8", "Lambdas", "In", "Action").stream().forEach(System.out::println);
	}
}
