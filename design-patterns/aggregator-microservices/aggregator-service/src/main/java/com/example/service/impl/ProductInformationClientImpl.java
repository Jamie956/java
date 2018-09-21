package com.example.service.impl;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.service.ProductInformationClient;

import java.io.IOException;

@Component
public class ProductInformationClientImpl implements ProductInformationClient {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductInformationClientImpl.class);

	@Override
	public String getProductTitle() {
		String response = null;
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			HttpGet httpGet = new HttpGet("http://localhost:8082/information");
			try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
				response = EntityUtils.toString(httpResponse.getEntity());
			}
		} catch (IOException e) {
			LOGGER.error("Exception caught.", e);
		}
		return response;
	}
}
