package com.example.dynamicproxy;

//目标对象
public class UserServiceImpl implements UserService {
	public void add() {
		System.out.println("--------------------add---------------");
	}
}