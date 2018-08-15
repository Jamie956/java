package com.example;

import java.util.HashMap;
import java.util.Map;

public class MapTests {
	public static void main(String[] args) {
		test01();
	}

	public static void test01() {
		Map<Integer, String> names = new HashMap<>();
		names.put(1, "a");
		names.put(2, "b");
		names.put(3, "c");
		
		names.forEach((key, value) -> System.out.println("Key: " + key + " Value: " + value));
	}
	
}
