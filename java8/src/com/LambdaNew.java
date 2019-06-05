package com;

import java.util.function.Supplier;

public class LambdaNew {
	public static void main(String[] args) {
		Supplier<Color> p = Color::new;
		p.get();
	}
}