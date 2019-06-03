package com.example.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvo implements InvocationHandler{
	private Object target;
	
	public MyInvo(Object target) {
		super();
		// TODO Auto-generated constructor stub
		this.target = target;
	}
	
	public void setProxyObject(Object target) {
		this.target = target;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("Before");
		Object obj = method.invoke(target, args);
		System.out.println("After");
		return obj;
	}
	
	public Object getProxyObject() {
		Object proxyInstance = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), target.getClass().getInterfaces(), this);
		return proxyInstance;
	}
}
