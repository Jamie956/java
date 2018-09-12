package com.example.domain;

import java.util.Map;

import com.example.AbstractDocument;

public class Car extends AbstractDocument implements HasModel, HasPrice, HasParts {
	public Car(Map<String, Object> properties) {
		super(properties);
	}
}
