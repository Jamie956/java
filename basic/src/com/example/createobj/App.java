package com.example.createobj;

public class App {
	public static void main(String[] args) {
		Child ch1 = new Child();
		ch1.function();
		System.out.println("===========");
		Child ch2 = new Child();
		ch2.function();
		/**
		 * Parent static block, b = 11 // b -> 12
		 * Child static block, y = 11 // y -> 12
		 * Parent block, a = 10 // a -> 11
		 * Parent block, b = 12 // b -> 13
		 * Parent construct no arg, a = 11
		 * Parent construct no arg, b = 13
		 * Child block, x = 10 // x -> 11
		 * Child block, y = 12 // y-> 13
		 * Child construct no arg, x = 11
		 * Child construct no arg, y = 13
		 * Child function
		 * ===========
		 * Parent block, a = 10 // a -> 11
		 * Parent block, b = 13 // b -> 14
		 * Parent construct no arg, a = 11
		 * Parent construct no arg, b = 14
		 * Child block, x = 10 // x -> 11
		 * Child block, y = 13 // y -> 14
		 * Child construct no arg, x = 11
		 * Child construct no arg, y = 14
		 * Child function
		 */
	}
}
