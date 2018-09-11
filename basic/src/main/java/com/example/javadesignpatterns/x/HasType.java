package com.example.javadesignpatterns.x;

public interface HasType extends Document {
	default void getHello() {
		hello();
	}
}
