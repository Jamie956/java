package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ApiGateway {
	@Resource
	private ImageClient imageClient;

	@Resource
	private PriceClient priceClient;

	@RequestMapping("/desktop")
	public DesktopProduct getProductDesktop() {
		DesktopProduct desktopProduct = new DesktopProduct();
		desktopProduct.setImagePath(imageClient.getImagePath());
		desktopProduct.setPrice(priceClient.getPrice());
		return desktopProduct;
	}

	@RequestMapping("/mobile")
	public MobileProduct getProductMobile() {
		MobileProduct mobileProduct = new MobileProduct();
		mobileProduct.setPrice(priceClient.getPrice());
		return mobileProduct;
	}
}
