package before_and_after;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class HelloAfterAdvice implements AfterReturningAdvice{

	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("after");
	}

}
