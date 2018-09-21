package com.example.domain;

import java.util.Optional;

import com.example.Document;

public interface HasType extends Document {
	String PROPERTY = "type";

	default Optional<String> getType() {
		return Optional.ofNullable((String) get(PROPERTY));
	}
}
