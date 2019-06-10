package com.example.abstract_factory;

public class Client {
	public Client(Factory factory) {
		factory.create();
	}
}
