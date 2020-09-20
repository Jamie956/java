package basic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 实现InvocationHandler接口
 * 重写invoke方法，负载调用代理类
 */
public class DynamicProxy implements InvocationHandler {
    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object ret = method.invoke(target, args);
        after();
        return ret;
    }

    private void before() {
        System.out.println("before");
    }

    private void after() {
        System.out.println("after");
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }
}