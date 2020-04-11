package com.example;


public class App {
	public static void main(String[] args) {
		try {
			User user = new User();
			user.setAddress(new Address("stress1"));
			User clone = (User) user.deepClone();
			
			System.out.println(user == clone);
			System.out.println(user.getAddress() == clone.getAddress());//深克隆克隆引用类型
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
