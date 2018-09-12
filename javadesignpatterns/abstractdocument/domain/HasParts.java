package com.example.javadesignpatterns.abstractdocument.domain;

import java.util.stream.Stream;

import com.example.javadesignpatterns.abstractdocument.Document;

public interface HasParts extends Document {
	String PROPERTY = "parts";

	default Stream<Part> getParts() {
		return children(PROPERTY, Part::new);
	}
}
