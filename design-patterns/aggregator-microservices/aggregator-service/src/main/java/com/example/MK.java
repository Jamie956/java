package com.example;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class MK {
	public static void main(String[] args) {
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			String url = "https://api.github.com/search/repositories?q=java+user:jamie956+language:java";
			HttpGet httpGet = new HttpGet(url);
			try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
				String res = EntityUtils.toString(httpResponse.getEntity());
				System.out.println("************************");
				System.out.println(res);
				System.out.println("************************");
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
