package com.example;

public class App {
	public static void main(String[] args) {
		test02();
	}

	public static void test01() {
		BusinessLookup businessLookup = new BusinessLookup();
		businessLookup.setEjbService(new EjbService());
		businessLookup.setJmsService(new JmsService());

		BusinessDelegate businessDelegate = new BusinessDelegate();
		businessDelegate.setLookupService(businessLookup);
		businessDelegate.setServiceType(ServiceType.EJB);

		Client client = new Client(businessDelegate);
		client.doTask();
	}

	public static void test02() {
		BusinessLookup businessLookup = new BusinessLookup();
		businessLookup.setEjbService(new EjbService());
		businessLookup.setJmsService(new JmsService());

		BusinessDelegate businessDelegate = new BusinessDelegate();
		businessDelegate.setLookupService(businessLookup);
		businessDelegate.setServiceType(ServiceType.JMS);

		Client client = new Client(businessDelegate);
		client.doTask();
	}

}
