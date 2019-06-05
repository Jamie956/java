package com.example.ch08;

import java.util.function.Supplier;

import com.example.Color;

public class LambdaNew {
	public static void main(String[] args) {
		Supplier<Color> p = Color::new;
		p.get();
	}
}