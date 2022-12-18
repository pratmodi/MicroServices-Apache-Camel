package com.in28minutes.microservices.camelmicroservicea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class CamelMicroserviceAApplication {
	
//	@Autowired
//	TutorialController tutController;
	
	private static ApplicationContext ctx;
	
	public static void main(String[] args) {
	//	ctx = new AnnotationConfigApplicationContext(CamelMicroserviceAApplication.class);
		SpringApplication.run(CamelMicroserviceAApplication.class, args);
	}
	
//	@EventListener(ApplicationReadyEvent.class)
//	public void doSomethingAfterStartup() {
//		tutController.getAllTutorials("Dummmy");
//	}

//	public static ApplicationContext getApplicationContext() {
//	    return ctx;
//	}
}
