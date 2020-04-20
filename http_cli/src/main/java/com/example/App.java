package com.example;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class App {
	public static void main(String[] args) {
		String url = "https://api.github.com/users/Jamie956/repos";
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse httpResponse;
		
		try {
			httpResponse = httpClient.execute(new HttpGet(url));
			String httpEntityStr = EntityUtils.toString(httpResponse.getEntity());
			System.out.println(httpEntityStr);
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
