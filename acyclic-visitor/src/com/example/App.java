package com.example;

import com.example.modem.*;
import com.example.visitor.ModemAVisitor;
import com.example.visitor.ModemBVisitor;

public class App {
	public static void main(String[] args) {
		test01();
	}

	public static class ConfigureVisitorX implements ModemAVisitor {
		public void visit(ModemA modemA) {
			System.out.println("ConfigureVisitorX");
			
			System.out.println("Before");
			System.out.println(modemA);
			System.out.println("After");
		}
	}
	
	public static void test01() {
		ModemA modemA = new ModemA();
		modemA.accept(new ConfigureVisitorX());
		modemA.accept(new ConfigureVisitorY());
	}
	
	public static class ConfigureVisitorY implements ModemAVisitor, ModemBVisitor {
		public void visit(ModemA modemA) {
			System.out.println("ConfigureVisitorY");
			
			System.out.println("Before");
			System.out.println(modemA);
			System.out.println("After");
			
		}

		public void visit(ModemB modemB) {
			System.out.println("ConfigureVisitorY");
			
			System.out.println("Before");
			System.out.println(modemB);
			System.out.println("After");
		}
	}
	public static void test02() {
		ModemB modemB = new ModemB();
		modemB.accept(new ConfigureVisitorY());
	}

}
