package com.springboot.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.web.entities.Product;
import com.springboot.web.repository.ProductRepository;

@RestController
public class ProductTestController {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(ProductTestController.class);
	
	@Autowired
	ProductRepository productRepository;
	
	@RequestMapping(value="/productsGetDetails/", method = RequestMethod.GET )
	public List<Product> getProducts(){
		LOGGER.info("Get all products Test");
		return productRepository.findAll();
	}
	
	@RequestMapping(value="/productDetailsById/{id}", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public Product getProductById(@PathVariable int id) {
		return productRepository.findById(id).get();
	}
	
	@RequestMapping(value="/createProduct/", method = RequestMethod.POST)
	public Product createProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}
	
	@RequestMapping(value="/updateProduct/", method = RequestMethod.PUT)
	public Product updateProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}
	
	@RequestMapping(value="/deleteProduct/", method= RequestMethod.DELETE)
	public void deleteProduct(@PathVariable int id) {
		productRepository.deleteById(id);
	}
}
