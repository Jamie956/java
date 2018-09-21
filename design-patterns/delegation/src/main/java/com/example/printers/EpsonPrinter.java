package com.example.printers;

import com.example.Printer;

public class EpsonPrinter implements Printer {
	@Override
	public void print(String message) {
		System.out.println("Epson Printer : " + message);
	}
}
