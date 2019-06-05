package com.example;

import java.util.*;

public class ArrayStream {
	public static void main(String[] args) {
		//List -> stream
        Arrays.asList("Java8", "Lambdas", "In", "Action").stream().forEach(System.out::println);
	}
}
