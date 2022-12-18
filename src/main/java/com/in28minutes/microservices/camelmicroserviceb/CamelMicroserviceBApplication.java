package com.in28minutes.microservices.camelmicroserviceb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class CamelMicroserviceBApplication {

//	@Autowired
//	RestConsumerClass restConsumerClass;
	
	public static void main(String[] args) {
		SpringApplication.run(CamelMicroserviceBApplication.class, args);
	}

//	@EventListener(ApplicationReadyEvent.class)
//	public void doSomethingAfterStartup() {
//		restConsumerClass.simpleGetMethod();
//	}
	
}
