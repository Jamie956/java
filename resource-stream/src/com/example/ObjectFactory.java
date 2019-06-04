package com.example;

import java.io.IOException;
import java.util.Properties;

public class ObjectFactory {
	private static Properties props;
	static {
		props = new Properties();
		try {
			props.load(
					ObjectFactory.class.getClassLoader().getResourceAsStream("com//example//app.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Object getInstance(String key) {
		Object obj = null;
		String value = props.getProperty(key);
		try {
			obj = Class.forName(value).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}
