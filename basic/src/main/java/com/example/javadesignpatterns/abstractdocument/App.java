package com.example.javadesignpatterns.abstractdocument;

import java.util.*;

import com.example.javadesignpatterns.abstractdocument.domain.*;

public class App {
	public static void main(String[] args) {
		test03();
	}

	public static void test01() {
		Map<String, Object> partProperties = new HashMap<>();
		partProperties.put(HasType.PROPERTY, "test type");
		partProperties.put(HasModel.PROPERTY, "test model");
		partProperties.put(HasPrice.PROPERTY, 100L);
		Part part = new Part(partProperties);

		System.out.println(part.getType().get());
		System.out.println(part.getModel().get());
		System.out.println(part.getPrice().get());
	}

	public static void test02() {
		Map<String, Object> carProperties = new HashMap<>();
		carProperties.put(HasModel.PROPERTY, "test car model");
		carProperties.put(HasPrice.PROPERTY, 12L);
		carProperties.put(HasParts.PROPERTY, Arrays.asList(new HashMap<>(), new HashMap<>()));
		Car car = new Car(carProperties);

		System.out.println(car.getModel().get());
		System.out.println(car.getPrice().get());
		System.out.println(car.getParts().count());
	}

	public static void test03() {
		Map<String, Object> carProperties = new HashMap<>();
		carProperties.put(HasModel.PROPERTY, "300SL");
		carProperties.put(HasPrice.PROPERTY, 10000L);

		Map<String, Object> wheelProperties = new HashMap<>();
		wheelProperties.put(HasType.PROPERTY, "wheel");
		wheelProperties.put(HasModel.PROPERTY, "15C");
		wheelProperties.put(HasPrice.PROPERTY, 100L);

		Map<String, Object> doorProperties = new HashMap<>();
		doorProperties.put(HasType.PROPERTY, "door");
		doorProperties.put(HasModel.PROPERTY, "Lambo");
		doorProperties.put(HasPrice.PROPERTY, 300L);

		carProperties.put(HasParts.PROPERTY, Arrays.asList(wheelProperties, doorProperties));

		Car car = new Car(carProperties);
		System.out.println(car.getModel().get());
		System.out.println(car.getPrice().get());
		car.getParts().forEach(
				p -> System.out.println(p.getType().get() + " - " + p.getModel().get() + " - " + p.getPrice().get()));

	}
}
