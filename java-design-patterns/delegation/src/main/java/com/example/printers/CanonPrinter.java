package com.example.printers;

import com.example.Printer;

public class CanonPrinter implements Printer {
	@Override
	public void print(String message) {
		System.out.println("Canon Printer : " + message);
	}
}
