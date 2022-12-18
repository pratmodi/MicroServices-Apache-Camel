package com.in28minutes.microservices.camelmicroservicea.routes.c;

import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Expression;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.crypto.CryptoDataFormat;
import org.apache.camel.spi.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.in28minutes.microservices.camelmicroservicea.CamelMicroserviceAApplication;
import com.in28minutes.microservices.camelmicroservicea.Practice;
import com.in28minutes.microservices.camelmicroservicea.TutorialController;

@Component
public class ActiveMqSenderRouter extends RouteBuilder {

	@Autowired
	Practice practice;
	
//	@Autowired
//	MySimpleProcessor mySimpleProcessor;
	
	@Autowired
	BeanGettingClass beanGettingClass;
	
	@Autowired
	TutorialController tutController;
	

	@Override
	public void configure() throws Exception {

		// timer
		from("timer:active-mq-timer?period=10000")
		.transform().constant("My message for Active MQ").log("${body}")
		.process(new BeanGettingClass())
		.log("${body}")
//		.marshal(createEncryptor())
		.to("activemq:my-activemq-queue");
		// queue

//		from("file:files/json")
//		.log("${body}")
//		.to("activemq:my-activemq-queue");

//		from("file:files/xml")
//		.log("${body}")
//		.to("activemq:my-activemq-xml-queue");

	}

	private CryptoDataFormat createEncryptor() throws KeyStoreException, IOException, NoSuchAlgorithmException,
			CertificateException, UnrecoverableKeyException {
		KeyStore keyStore = KeyStore.getInstance("JCEKS");
		ClassLoader classLoader = getClass().getClassLoader();
		keyStore.load(classLoader.getResourceAsStream("myDesKey.jceks"), 
				"someKeystorePassword".toCharArray());
		Key sharedKey = keyStore.getKey("myDesKey", "someKeyPassword".toCharArray());

		CryptoDataFormat sharedKeyCrypto = new CryptoDataFormat("DES", sharedKey);
		return sharedKeyCrypto;
	}
	
//	@Component
	class MySimpleProcessor implements Processor {
		
		private Logger logger = LoggerFactory.getLogger(MySimpleProcessor.class);

		@Override
		public void process(Exchange exchange) throws Exception {
		//	exchange.getMessage().setBody("MY PROCESSOR");
	//		exchange.getIn().setBody("XYZ");
	//		if (exchange.getMessage().getBody() != null) {
				logger.info("MySimpleProcessor {} {}", "XYZ", practice.exercise1a());
				logger.info(String.format("Message {}", "prateek"));
			}
		}

	@Component
	class BeanGettingClass implements Processor {

//		CamelMicroserviceAApplication cma = new CamelMicroserviceAApplication();
		
//		Object getToysAfterDiscount = ctx.getBean("getToysAfterDiscount");
		//ctx.getBean(TutorialController.class);
		
		private Logger logger = LoggerFactory.getLogger(MySimpleProcessor.class);

		@Override
		public void process(Exchange exchange) throws Exception {
//			TutorialController getToysAfterDiscount = cma.getApplicationContext().getBean(TutorialController.class);
			//	exchange.getMessage().setBody("MY PROCESSOR");
	//		exchange.getIn().setBody("XYZ");
	//		if (exchange.getMessage().getBody() != null) {
			Message in = exchange.getIn(); 
			in.setBody(tutController.getToysAfterDiscount());
			
				logger.info("BeanGettingClass {}", tutController.getToysAfterDiscount());
			}
		}
	
	}


