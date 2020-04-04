package before_and_after;

import org.springframework.aop.framework.ProxyFactory;

public class App {
	public static void main(String[] args) {
		ProxyFactory pf = new ProxyFactory();
		pf.setTarget(new HelloImpl());
		pf.addAdvice(new HelloBeforeAdvice());
		pf.addAdvice(new HelloAfterAdvice());
		
		IHello helloProxy = (IHello)pf.getProxy();
		helloProxy.greeting("Jamie");
	}
}
