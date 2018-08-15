package com.example;

import java.math.BigInteger;
import java.security.interfaces.RSAKey;

public class BigIntegerTests {
	public static void main(String[] args) {
		test03();
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
		
		rs = BigInteger.ZERO;
		rs = BigInteger.ONE;
		rs = BigInteger.TEN;
	}
	
	public static void test02() {
		//算术
		BigInteger a = new BigInteger("10");
		BigInteger b = new BigInteger("10");
		//+
		BigInteger rs = a.add(b);
		System.out.println(rs);
		//-
		rs = a.subtract(b);
		System.out.println(rs);
		// /
		rs = a.divide(b);
		System.out.println(rs);
		//*
		rs = a.multiply(b);
		System.out.println(rs);
		// ^
		rs = a.pow(3);
		System.out.println(rs);
		// %
		rs = a.remainder(b);
		System.out.println(rs);
		//最大公约数
		rs = a.gcd(b);
		System.out.println(rs);
		//最大值
		rs = a.max(b);
		System.out.println(rs);
		//最小值
		rs = a.min(b);
		System.out.println(rs);
	}
	
	public static void test03() {
		BigInteger a = BigInteger.valueOf(1);
		BigInteger b = BigInteger.valueOf(2);
		//大小比较
		a.equals(b);
	}
}













