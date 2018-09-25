package com.example;

import com.example.configure.*;
import com.example.modem.*;

public class App {
	public static void main(String[] args) {
		test02();
	}

	public static void test01() {
		ModemA modemA = new ModemA();
		modemA.accept(new ConfigureForUnixVisitor());
		modemA.accept(new ConfigureForDosVisitor());
	}

	public static void test02() {
		ModemB modemB = new ModemB();
		modemB.accept(new ConfigureForDosVisitor());
	}

}
