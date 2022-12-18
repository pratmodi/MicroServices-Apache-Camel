package com.in28minutes.microservices.camelmicroserviceb;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

	private Long id;

	private LocalDate orderDate;

	private LocalDate deliveryDate;
	
	private String status;
	
	private Customer customer;
	
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	Set<Product> products;
}
