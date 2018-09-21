package com.example;

import java.util.concurrent.Callable;

public class App {
	public static void main(String[] args) throws Exception {
		test04();
	}

	public static void test01() throws Exception {
		AsyncExecutor executor = new ThreadAsyncExecutor();
		AsyncResult<Integer> asyncRs = executor.startProcess(lazyval(10, 500));
		
		Thread.sleep(350);
		System.out.println("Some hard work done");

		Integer rs = executor.endProcess(asyncRs);
		System.out.println("Result: " + rs);
	}
	
	public static void test02() throws Exception {
		AsyncExecutor executor = new ThreadAsyncExecutor();
		AsyncResult<String> asyncRs = executor.startProcess(lazyval("test", 300));
		
		Thread.sleep(350);
		System.out.println("Some hard work done");

		String rs = executor.endProcess(asyncRs);
		System.out.println("Result: " + rs);
	}
	
	public static void test03() throws Exception {
		AsyncExecutor executor = new ThreadAsyncExecutor();
		AsyncResult<Long> asyncRs = executor.startProcess(lazyval(50L, 700));
		
		Thread.sleep(350);
		System.out.println("Some hard work done");

		Long rs = executor.endProcess(asyncRs);
		System.out.println("Result: " + rs);
	}
	
	public static void test04() throws Exception {
		AsyncExecutor executor = new ThreadAsyncExecutor();
		AsyncResult<Integer> asyncRs = executor.startProcess(lazyval(20, 400), callback("Callback result 4"));
		
		Thread.sleep(350);
		System.out.println("Some hard work done");

		asyncRs.await();
	}
	
	public static void test05() throws Exception {
		AsyncExecutor executor = new ThreadAsyncExecutor();
		AsyncResult<String> asyncRs = executor.startProcess(lazyval("callback", 600), callback("Callback result 5"));
		
		Thread.sleep(350);
		System.out.println("Some hard work done");
		
		asyncRs.await();
	}
	
	// Return Callable function that return the value
	private static <T> Callable<T> lazyval(T value, long delayMillis) {
		return () -> {
			Thread.sleep(delayMillis);
			System.out.println("Task completed with: " + value);
			return value;
		};
	}

	private static <T> AsyncCallback<T> callback(String name) {
		return (value, ex) -> {
			if (ex.isPresent()) {
				System.out.println(name + " failed: " + ex.map(Exception::getMessage).orElse(""));
			} else {
				System.out.println(name + ": " + value);
			}
		};
	}
}
