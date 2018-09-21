package com.example;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class App {
	public static void main(String[] args) {
		test04();
	}

	public static void test01() {
		SimpleWizard simpleWizard = new SimpleWizard();
		simpleWizard.smoke();
	}

	public static void test02() {
		AdvancedWizard advancedWizard = new AdvancedWizard(new SecondBreakfastTobacco());
		advancedWizard.smoke();
	}

	public static void test03() {
		AdvancedSorceress advancedSorceress = new AdvancedSorceress();
		advancedSorceress.setTobacco(new SecondBreakfastTobacco());
		advancedSorceress.smoke();
	}

	public static void test04() {
		Injector injector = Guice.createInjector(new TobaccoModule());
		GuiceWizard guiceWizard = injector.getInstance(GuiceWizard.class);
		guiceWizard.smoke();
	}

}
