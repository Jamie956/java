package com.example;

public class HashCodeTest {
	public static void main(String[] args) {
		String s = "Ok";//2556
		StringBuilder sb = new StringBuilder(s);//366712642
		String t = new String("Ok");//2556
		StringBuilder tb = new StringBuilder(t);//1829164700
	}
}
