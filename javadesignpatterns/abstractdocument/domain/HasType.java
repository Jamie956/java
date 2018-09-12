package com.example.javadesignpatterns.abstractdocument.domain;

import java.util.Optional;

import com.example.javadesignpatterns.abstractdocument.Document;

public interface HasType extends Document {
	String PROPERTY = "type";

	default Optional<String> getType() {
		return Optional.ofNullable((String) get(PROPERTY));
	}
}
