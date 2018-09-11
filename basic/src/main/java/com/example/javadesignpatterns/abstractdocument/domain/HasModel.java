package com.example.javadesignpatterns.abstractdocument.domain;

import java.util.Optional;

import com.example.javadesignpatterns.abstractdocument.Document;

public interface HasModel extends Document {
	String PROPERTY = "model";

	default Optional<String> getModel() {
		return Optional.ofNullable((String) get(PROPERTY));
	}
}
