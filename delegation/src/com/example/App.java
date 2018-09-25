package com.example;

public class App {
	public static final String MESSAGE_TO_PRINT = "hello world";

	public static void main(String[] args) {
		test02();
	}
	
	public static void test01() {
		PrinterController pc = new PrinterController(new CanonPrinter());
		pc.print(MESSAGE_TO_PRINT);
	}
	
	public static void test02() {
		PrinterController pc = new PrinterController(new EpsonPrinter());
		pc.print(MESSAGE_TO_PRINT);
	}
}
