package com.example.v7;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(IsActive.class)
public class Item {
	public Item() {
		super();
		System.out.println("Item cons");
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
