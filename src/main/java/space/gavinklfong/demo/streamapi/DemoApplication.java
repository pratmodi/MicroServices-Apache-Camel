package space.gavinklfong.demo.streamapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import space.gavinklfong.demo.streamapi.practice.Practice;

@SpringBootApplication
public class DemoApplication {
	
	@Autowired
	private Practice practice;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
	    practice.exercise1();
	}
}
