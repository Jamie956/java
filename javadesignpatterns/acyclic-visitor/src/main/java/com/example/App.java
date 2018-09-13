package com.example;

import com.example.configure.ConfigureForDosVisitor;
import com.example.configure.ConfigureForUnixVisitor;
import com.example.modem.Hayes;
import com.example.modem.Zoom;

public class App {
	public static void main(String[] args) {
		test01();
	}

	public static void test01() {
		Hayes hayes = new Hayes();
		hayes.accept(new ConfigureForDosVisitor()); // Hayes modem with Unix configurator
		hayes.accept(new ConfigureForUnixVisitor()); // Hayes modem with Unix configurator
	}

	public static void test02() {
		Zoom zoom = new Zoom();
		zoom.accept(new ConfigureForDosVisitor()); // Zoom modem with Dos configurator
		zoom.accept(new ConfigureForUnixVisitor()); // Zoom modem with Unix configurator
	}

	public static void test03() {
		// test ConfigureForDosVisitor
		ConfigureForDosVisitor conDos = new ConfigureForDosVisitor();
		conDos.visit(new Zoom());
		conDos.visit(new Hayes());
	}

	public static void test04() {
		// test ConfigureForUnixVisitor
		ConfigureForUnixVisitor conUnix = new ConfigureForUnixVisitor();
		conUnix.visit(new Zoom());
	}
}
