package com.example;

public class GenericsTest {

	public static void main(String[] args) {
		//定义泛型，类里的方法参数类型也就确定了
	    Point<String> p = new Point<>() ;
	    p.setX("1");
	    System.out.println(p.x);
	}

	static class Point<T> {
		public T x ;
		public void setX(T x) {
			this.x = x;
		}
	}
}

