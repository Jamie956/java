package com.example;

import java.math.BigInteger;

public class BigIntegerTests {
	public static void main(String[] args) {
		test01();
	}
	public static void test01() {
		long a = Long.MAX_VALUE;
		int b = Integer.MIN_VALUE;
		String c = "-1";
		//long转BigInteger
		BigInteger rs = BigInteger.valueOf(a);
		System.out.println(rs);
		//int转BigInteger
		rs = BigInteger.valueOf(b);
		System.out.println(rs);
		//string转BigInteger
		rs = new BigInteger(c);
		System.out.println(rs);
		//10进制转2进制
		String binaryString = "110";
		rs = new BigInteger(binaryString , 2);
		System.out.println(rs);
	}
}
