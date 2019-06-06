package com.example.v5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Animal {
	@Autowired
	private Cat cat;
	
	public void say() {
		cat.sayhi();
	}
	
}