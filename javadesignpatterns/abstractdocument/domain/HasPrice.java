package com.example.javadesignpatterns.abstractdocument.domain;

import java.util.Optional;

import com.example.javadesignpatterns.abstractdocument.Document;

public interface HasPrice extends Document {
	String PROPERTY = "price";

	default Optional<Number> getPrice() {
		return Optional.ofNullable((Number) get(PROPERTY));
	}
}
