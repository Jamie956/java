package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InformationController {
	@RequestMapping(value = "/information", method = RequestMethod.GET)
	public String getProductTitle() {
		return "The Product Title.";
	}
}
