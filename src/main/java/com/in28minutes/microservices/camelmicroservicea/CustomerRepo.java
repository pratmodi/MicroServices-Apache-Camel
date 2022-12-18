package com.in28minutes.microservices.camelmicroservicea;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends CrudRepository<com.in28minutes.microservices.camelmicroservicea.Customer, Long> {

	List<com.in28minutes.microservices.camelmicroservicea.Customer> findAll();
}
