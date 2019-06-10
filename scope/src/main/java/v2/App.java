package v2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
		
		System.out.println(context.getBean("userBean") == context.getBean("userBean"));
		
		context.close();
	}
}
