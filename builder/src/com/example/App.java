package com.example;

public class App {
	public static void main(String[] args) {
		MealA a = new MealA();
		Waiter waiter = new Waiter(a);
		Meal mealA = waiter.construct();
		System.out.print("套餐A的组成部分:");
		System.out.println("食物：" + mealA.getFood() + "；   " + "饮品：" + mealA.getDrink());
	}
}
