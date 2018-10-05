package com.example.factory;

import java.io.IOException;
import java.util.Properties;

public class ObjectFactory {
	private static Properties props;
	static {
		props = new Properties();
		try {
			props.load(
					ObjectFactory.class.getClassLoader().getResourceAsStream("com//example//config//app.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Object getInstance(String logicalclassName) {
		Object obj = null;
		String originalclassName = props.getProperty(logicalclassName);
		try {
			obj = Class.forName(originalclassName).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}
