package com.springboot.web;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.springboot.web.entities.Product;

@SpringBootTest
class ProductrestapiApplicationTests {
	
	@Value("${productrestapi.services.url}")
	private String baseURL;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductrestapiApplicationTests.class);

	@Test
	void testGetProducts() {
		try {
		System.out.println(baseURL);
		RestTemplate restTemplate = new RestTemplate();
		Product product = restTemplate.getForObject(baseURL+"2", Product.class);
		assertNotNull(product);
		assertEquals("Mac Book",product.getName());
		}
		catch(Exception e) {
			LOGGER.info(e.getMessage());
		}
		
	}
	
	@Test
	void testCreateProducts() {
		RestTemplate restTemplate = new RestTemplate();
		Product product = new Product();
		product.setName("Samsung");
		product.setDescription("Its just wow !!");
		product.setPrice(3000);
		Product newProduct = restTemplate.postForObject(baseURL, product, Product.class);
		assertNotNull(newProduct);
		assertNotNull(newProduct.getId());
		assertEquals("Samsung",newProduct.getName());
		
	}
	
	@Test
	void testUpdateProducts() {
		
		RestTemplate restTemplate = new RestTemplate();
		Product product = restTemplate.getForObject(baseURL+"2", Product.class);
		product.setPrice(5200);
		
		restTemplate.put(baseURL, product);
	
	}

}
