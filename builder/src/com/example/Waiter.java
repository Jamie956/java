package com.example;

public class Waiter {
	private MealBuilder mealBuilder;

	public Waiter(MealBuilder mealBuilder) {
		this.mealBuilder = mealBuilder;
	}

	public Meal construct() {
		mealBuilder.buildFood();
		mealBuilder.buildDrink();
		return mealBuilder.getMeal();
	}
}
