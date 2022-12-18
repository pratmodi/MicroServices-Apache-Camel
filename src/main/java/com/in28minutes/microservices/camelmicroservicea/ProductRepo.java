package com.in28minutes.microservices.camelmicroservicea;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import space.gavinklfong.demo.streamapi.models.Product;

@Repository
public interface ProductRepo extends CrudRepository<com.in28minutes.microservices.camelmicroservicea.Product,Long> {

	List<com.in28minutes.microservices.camelmicroservicea.Product> findAll();
}
