package v3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

		WindowGenerator wg = (WindowGenerator) context.getBean("windowGeneratorBean");
		Window w = wg.newWindow();
		Window w2 = wg.newWindow();
		System.out.println(w == w2);
		
		context.close();
	}
}
