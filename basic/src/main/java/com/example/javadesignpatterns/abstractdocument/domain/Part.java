package com.example.javadesignpatterns.abstractdocument.domain;

import java.util.Map;

import com.example.javadesignpatterns.abstractdocument.AbstractDocument;

public class Part extends AbstractDocument implements HasType, HasModel, HasPrice {
	public Part(Map<String, Object> properties) {
		super(properties);
	}
}
