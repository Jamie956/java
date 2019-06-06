package com.example.v7;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(IsActive.class)
public class Item {
	public Item() {
		System.out.println("Item");
	}
}
