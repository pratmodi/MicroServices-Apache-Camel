package com.in28minutes.microservices.camelmicroservicea;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class RouterForStreamsData extends RouteBuilder {

	
	@Autowired
	private Practice practice;
	
	
	@Override
	public void configure() throws Exception {
		from("timer:first-timer") //null
		
//		.bean(loggingComponent)
//		.log("${body}")
		.process(new MySimpleProcessor())
		.to("log:first-timer"); //database
		
	}
	
	@Component
	class MySimpleProcessor implements Processor {
		
		private Logger logger = LoggerFactory.getLogger(MySimpleProcessor.class);

		@Override
		public void process(Exchange exchange) throws Exception {
			exchange.getIn().setBody("FROM ROUTERFORSTREAMSDATA");
			List<com.in28minutes.microservices.camelmicroservicea.Product> list = practice.exercise3();
			logger.info("MySimpleProcessor {} {}", exchange.getMessage().getBody(),list);

		}

	}

}
