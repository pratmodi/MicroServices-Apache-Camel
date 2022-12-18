package com.in28minutes.microservices.camelmicroserviceb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

//@Component
public class RestConsumerClass {

	private Logger logger = LoggerFactory.getLogger(RestConsumerClass.class);

	public Product[] simpleGetMethod() {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8090/api/toysafterdiscount";
		Product[] response = restTemplate.getForObject(url, Product[].class);
		for(Product ce:response) {
			logger.info("*&*&*&**&*&*&**&*&*&*&*&*&**&*&*&&*&*&"+ce);
		}
		
		return response;
	}

}
