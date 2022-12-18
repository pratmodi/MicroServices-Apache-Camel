package com.in28minutes.microservices.camelmicroserviceb;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	private Long id;
	
	private String name;
	
	private Integer tier;
	
}
