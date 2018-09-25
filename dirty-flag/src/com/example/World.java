package com.example;

import java.util.ArrayList;
import java.util.List;

public class World {
	private List<String> countries;
	private DataFetcher df;

	public World() {
		this.countries = new ArrayList<String>();
		this.df = new DataFetcher();
	}

	public List<String> fetch() {
		List<String> data = df.fetch();
		countries = data.isEmpty() ? countries : data;
		return countries;
	}
}
