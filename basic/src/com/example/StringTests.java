package com.example;

public class StringTests {
	public static void main(String[] args) {
		test02();
	}
	
	public static void test01() {
		String s1 = "ab";
		String s2 = "cd";
		String s3 = "ab";
		
		System.out.println(s1.hashCode());//3105
		System.out.println(s2.hashCode());//3169
		System.out.println(s3.hashCode());//3105
		
		/**
		 * Conclusion:
		 * 1. String value not equal -> hashcode not equal.
		 * 2. String value equal -> hashcode equal, no need to create new String.
		 */
	}
	
	public static void test02() {
		//A
		StringBuilder sb1 = new StringBuilder();
		long start1 = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			sb1.append(i);
		}
		long end1 = System.currentTimeMillis();
		System.err.println(end1-start1);
		
		//B
		StringBuffer sb2 = new StringBuffer();
		long start2 = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			sb2.append(i);
		}
		long end2 = System.currentTimeMillis();
		System.err.println(end2-start2);
		
		/**
		 * Conclusion:
		 * StringBuilder faster than StringBuffer, cause the lock.
		 */
	}
	
	public static void test03() {

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
