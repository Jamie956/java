package v1;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("v1/beans.xml");

		Generator wg = (Generator) context.getBean("generatorBean");
		System.out.println(wg.create() == wg.create());
		
		context.close();
	}
}
