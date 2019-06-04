package com.example;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {
	private static List<String> countries = new ArrayList<String>();
	private static DataFetcher dataFetcher = new DataFetcher();
	
	public static void main(String[] args) {
		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		
		executorService.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				
				List<String> data = dataFetcher.fetch();
				countries = data.isEmpty() ? countries : data;
				System.out.println(countries);
				
			}
		}, 0, 10, TimeUnit.SECONDS);
		
	}
}
