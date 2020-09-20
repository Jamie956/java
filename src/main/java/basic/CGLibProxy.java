package basic;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CGLibProxy implements MethodInterceptor{
	private static CGLibProxy instance = new CGLibProxy();

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		before();
		Object ret = proxy.invokeSuper(obj, args);
		after();
		return ret;
	}
	
	private void after() {
		System.out.println("after");
	}

	private void before() {
		System.out.println("before");
	}

	@SuppressWarnings("unchecked")
	public <T> T getProxy(Class<T> clazz) {
		return (T)Enhancer.create(clazz, this);
	}
	
	public static CGLibProxy getInstance() {
		return instance;
	}
}
