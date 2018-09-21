package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayTests {
	public static void main(String[] args) {
		test01();
	}

	public static void test01() {
		int[] a = new int[3];
		int[] b = { 1, 2, 3 };
		int[] c = new int[] { 1, 2, 3 };
		int[][] d = { { 1, 2 }, { 3, 4, 5 } };
		int[][] e = new int[5][];
		int[][] f = new int[5][4];
		
		//数组转字符串
		String rs = Arrays.toString(b);
		System.out.println(rs);
		//数组排序
		Arrays.sort(b);
		//获取元素索引
		int index = Arrays.binarySearch(b,2);
		System.out.println(index);
	}
	public static void test02() {
		int[] b = { 1, 2, 3 };
	for (int i : b) {
		System.out.print(i + " ");
		}
	}
	
	public static void test03() {
		String[] arr = {"foo", "bar", "baz"};
		List<String> list = new ArrayList<>(Arrays.asList(arr));
		System.out.println(list);
		
		ArrayList<String> list2 = new ArrayList<>();
		list2.addAll(Arrays.asList(arr));
		System.out.println(list2);
	}
}
