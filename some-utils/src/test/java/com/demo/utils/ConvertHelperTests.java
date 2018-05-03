package com.demo.utils;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.utils.ConvertHelper;

public class ConvertHelperTests {
	@Test
	public void strToIntTest() {
		int result = ConvertHelper.strToInt("1233");
		System.out.println(result);
	}
	
	@Test
	public void strToLongTest() {
		Long result = ConvertHelper.strToLong("2342432");
		System.out.println(result);
	}
	
	@Test
	public void strToFloatTest() {
		Float result = ConvertHelper.strToFloat("2342432");
		System.out.println(result);
	}
	
	@Test
	public void strToDoubleTest() {
		Double result = ConvertHelper.strToDouble("2342432");
		System.out.println(result);
	}
	
	@Test
	public void strToArryTest() {
		Object[] result = ConvertHelper.strToArry("a");
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}
	
	@Test
	public void strArrayToIntArrayTest() {
		String[] strArr = {"1", "3", "2"};
		int[] result = ConvertHelper.strArrayToIntArray(strArr);
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}
	
	@Test
	public void arrToStringTest() {
		Object[] obj = { "a", "b" };
		String result = ConvertHelper.arrToString(obj);
		System.out.println(result);
	}
	
	@Test
	public void arrToStringTest2() {
		Object[] obj = { "a", "b", "c" };
		String result = ConvertHelper.arrToString(obj, 1);
		System.out.println(result);
	}
	
	@Test
	public void arrToStringTest3() {
		Object[] obj = { "a", "b", "c" };
		String result = ConvertHelper.arrToString(obj, "-");
		System.out.println(result);
	}
	
	@Test
	public void listToStringTest() {
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		
		String result = ConvertHelper.listToString(list);
		System.out.println(result);
	}
	
	@Test
	public void listToStringTest2() {
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		
		String result = ConvertHelper.listToString(list, "-");
		System.out.println(result);
	}
	
	@Test
	public void listToStringArrayTest() {
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		String[] result = ConvertHelper.listToStringArray(list);
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}
	
	@Test
	public void strArrDoPackTest() {
		String[] strArr = { "a", "b", "c" };
		String[] result = ConvertHelper.strArrDoPack(strArr, "$", "~");
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}
	
	@Test
	public void strArrDoPackTest2() {
		String[] strArr = { "a", "b", "c" };
		String[] result = ConvertHelper.strArrDoPack(strArr, "$", "~", 3);
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}
	
	//?????
	@Test
	public void strArrDoPackTest3() {
		String[] strArr = { "a", "b", "c" };
		String[] result = ConvertHelper.strArrDoPack(strArr, "1", "2", 2, 1);
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}
	
	@Test
	public void createStrTest() {
		String result = ConvertHelper.createStr("a", 3);
		System.out.println(result);
	}
	
	@Test
	public void createStrTest2() {
		String result = ConvertHelper.createStr("a", 3, "-");
		System.out.println(result);
	}
	
	@Test
	public void createStrArrTest() {
		String[] result = ConvertHelper.createStrArr("a", 3);
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}
	
	@Test
	public void replaceAllSignTest() {
		String result = ConvertHelper.replaceAllSign("abc123!@#$%^&*(_");
		System.out.println(result);
	}
	
	@Test
	public void stepNumInStrTest() {
		String result = ConvertHelper.stepNumInStr("b121a", false);
		System.out.println(result);
	}
	
}
