package v4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
		User u1 = (User) context.getBean("userBean");
		
		u1.setName("Jamie");
		
		User u2 = (User) context.getBean("userBean");
		
		System.out.println(u1.getName());
		System.out.println(u2.getName());
		
		context.close();
	}
}
