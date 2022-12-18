package com.in28minutes.microservices.camelmicroserviceb.routes;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.crypto.CryptoDataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.in28minutes.microservices.camelmicroserviceb.CurrencyExchange;
import com.in28minutes.microservices.camelmicroserviceb.Product;
import com.in28minutes.microservices.camelmicroserviceb.RestConsumerClass;

@Component
public class ActiveMqReceiverRouter extends RouteBuilder {

//	@Autowired
//	private MyCurrencyExchangeProcessor myCurrencyExchangeProcessor;
//
//	@Autowired
//	private MyCurrencyExchangeTransformer myCurrencyExchangeTransformer;

	@Override
	public void configure() throws Exception {

		from("activemq:my-activemq-queue")
		// .unmarshal(createEncryptor())
//		.unmarshal()
				.bean(new RestConsumer()).log("${body}")
//		.json(JsonLibrary.Jackson, CurrencyExchange.class)
//		.bean(myCurrencyExchangeProcessor)
//		.bean(myCurrencyExchangeTransformer)
				// .to("log:received-message-from-active-mq")

//		from("activemq:my-activemq-xml-queue")
//		.unmarshal()
//		.jacksonxml(CurrencyExchange.class)
//		.to("log:received-message-from-active-mq");

//		from("activemq:split-queue")
				.to("log:received-message-from-active-mq");

	}

	private CryptoDataFormat createEncryptor() throws KeyStoreException, IOException, NoSuchAlgorithmException,
			CertificateException, UnrecoverableKeyException {
		KeyStore keyStore = KeyStore.getInstance("JCEKS");
		ClassLoader classLoader = getClass().getClassLoader();
		keyStore.load(classLoader.getResourceAsStream("myDesKey.jceks"), "someKeystorePassword".toCharArray());
		Key sharedKey = keyStore.getKey("myDesKey", "someKeyPassword".toCharArray());

		CryptoDataFormat sharedKeyCrypto = new CryptoDataFormat("DES", sharedKey);
		return sharedKeyCrypto;
	}

}

//@Component
class MyCurrencyExchangeProcessor {

	Logger logger = LoggerFactory.getLogger(MyCurrencyExchangeProcessor.class);

	public void processMessage(CurrencyExchange currencyExchange) {

		logger.info("Do some processing wiht currencyExchange.getConversionMultiple() value which is {}",
				currencyExchange.getConversionMultiple());

	}
}

//@Component
class MyCurrencyExchangeTransformer {

	Logger logger = LoggerFactory.getLogger(MyCurrencyExchangeProcessor.class);

	public CurrencyExchange processMessage(CurrencyExchange currencyExchange) {

		currencyExchange.setConversionMultiple(currencyExchange.getConversionMultiple().multiply(BigDecimal.TEN));

		return currencyExchange;

	}
}

@Component
class RestConsumer implements Processor {

	private RestConsumerClass restConsumerClass = new RestConsumerClass();

	private Logger logger = LoggerFactory.getLogger(RestConsumer.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		// exchange.getMessage().setBody("MY PROCESSOR");
		// exchange.getIn().setBody("XYZ");
		// if (exchange.getMessage().getBody() != null) {

		if (this.restConsumerClass != null) {
			Message in = exchange.getIn();
			for (Product p : this.restConsumerClass.simpleGetMethod()) {
				in.setBody(p);
			}
			logger.info("^^^^^^^RestConsumer {} {}", "XYZ", restConsumerClass.simpleGetMethod());

		}
	}
}
