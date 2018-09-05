package com.example.java8inaction.ch03;

import java.io.*;

public class ExecuteAround {
	public static void main(String[] args) throws IOException {
		test03();
	}

	public static final String DATA_PATH = "src/main/java/com/example/java8inaction/ch03/data.txt";

	public static void test01() throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(DATA_PATH))) {
			System.out.println(br.readLine());
		}
	}

	public static void test02() throws IOException {
		BufferedReaderProcessor p = (BufferedReader b) -> b.readLine();
		try (BufferedReader br = new BufferedReader(new FileReader(DATA_PATH))) {
			System.out.println(p.process(br));
		}
	}

	public static void test03() throws IOException {
		BufferedReaderProcessor p = (BufferedReader b) -> b.readLine() + b.readLine();
		try (BufferedReader br = new BufferedReader(new FileReader(DATA_PATH))) {
			System.out.println(p.process(br));
		}
	}

	public interface BufferedReaderProcessor {
		public String process(BufferedReader b) throws IOException;

	}
}
