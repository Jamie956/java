package v1;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("v1/beans.xml");
		WindowGenerator windowGenerator = (WindowGenerator) context.getBean("windowGenerator");
		
		Window window = windowGenerator.createWindow();
		Window window2 = windowGenerator.createWindow();
		
		System.out.println(window == window2);
		
		context.close();
	}
}
