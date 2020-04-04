package before_and_after;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class HelloBeforeAdvice implements MethodBeforeAdvice{

	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("before");
	}

}
