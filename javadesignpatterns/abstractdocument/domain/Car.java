package com.example.javadesignpatterns.abstractdocument.domain;

import java.util.Map;

import com.example.javadesignpatterns.abstractdocument.AbstractDocument;

public class Car extends AbstractDocument implements HasModel, HasPrice, HasParts {
	public Car(Map<String, Object> properties) {
		super(properties);
	}
}
