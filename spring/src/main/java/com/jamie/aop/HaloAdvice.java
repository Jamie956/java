package com.jamie.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * 增强的前后方法
 */
public class HaloAdvice implements MethodBeforeAdvice, AfterReturningAdvice, MethodInterceptor {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("afterReturning");
    }

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("before");
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("around before");
        Object obj = invocation.proceed();
        System.out.println("around after");
        return obj;
    }
}