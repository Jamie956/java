package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataFetcher {
	private long lastFetched;

	public DataFetcher() {
		this.lastFetched = -1;
	}

	private boolean isDirty(long fileLastModified) {
		if (lastFetched != fileLastModified) {
			lastFetched = fileLastModified;
			return true;
		}
		return false;
	}

	public List<String> fetch() {
		File file = new File("src/com/example/world.txt");
		if (isDirty(file.lastModified())) {
			List<String> data = new ArrayList<String>();
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String line;
				while ((line = br.readLine()) != null) {
					data.add(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return data;
		}

		return new ArrayList<String>();
	}
}
