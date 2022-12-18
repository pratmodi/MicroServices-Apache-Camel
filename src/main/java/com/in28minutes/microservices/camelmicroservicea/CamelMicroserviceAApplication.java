package com.in28minutes.microservices.camelmicroservicea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.ApplicationContext;


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
