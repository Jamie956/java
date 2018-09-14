package com.example;

import com.example.printers.CanonPrinter;
import com.example.printers.EpsonPrinter;
import com.example.printers.HpPrinter;

public class App {
	public static final String MESSAGE_TO_PRINT = "hello world";

	public static void main(String[] args) {
		test03();
	}
	
	public static void test01() {
		PrinterController pc = new PrinterController(new HpPrinter());
		pc.print(MESSAGE_TO_PRINT);
	}
	
	public static void test02() {
		PrinterController pc = new PrinterController(new CanonPrinter());
		pc.print(MESSAGE_TO_PRINT);
	}
	
	public static void test03() {
		PrinterController pc = new PrinterController(new EpsonPrinter());
		pc.print(MESSAGE_TO_PRINT);
	}
}
