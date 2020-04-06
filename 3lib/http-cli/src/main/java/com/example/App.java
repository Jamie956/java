package com.example;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class App {
	public static void main(String[] args) {
		String url = "https://api.github.com/users/Jamie956/repos";
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse httpResponse = httpClient
						.execute(new HttpGet(url))
						) {
			String httpEntityStr = EntityUtils.toString(httpResponse.getEntity());
			System.out.println(httpEntityStr);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
