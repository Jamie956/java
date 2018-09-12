package com.example.domain;

import java.util.Map;

import com.example.AbstractDocument;

public class Part extends AbstractDocument implements HasType, HasModel, HasPrice {
	public Part(Map<String, Object> properties) {
		super(properties);
	}
}
