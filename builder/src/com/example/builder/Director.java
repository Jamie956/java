package com.example.builder;

public class Director {
	public Director(Builder builder) {
		builder.buildA();
		builder.buildB();
	}
}
