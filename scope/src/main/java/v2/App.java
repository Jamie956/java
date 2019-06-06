package v2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

		Generator g = context.getBean(Generator.class);
		System.out.println(g.create() == g.create());

		context.close();
	}
}
