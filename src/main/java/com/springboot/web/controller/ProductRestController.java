package com.springboot.web.controller;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.web.entities.Product;
import com.springboot.web.repository.ProductRepository;

@RestController
public class ProductRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping(value = "/products/")
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	@GetMapping(value = "/products/{id}")
	@Transactional(readOnly = true)
	//@Cacheable("product-cache")
	public EntityModel<Product> getProductById(@PathVariable("id") int id) {
		LOGGER.info("Product details by id:" + id);
		Product product = new Product();
		product = productRepository.findById(id).get();
		
		EntityModel<Product> model = EntityModel.of(product);
		
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).getProducts());
		model.add(linkToUsers.withRel("all-products"));
		
		return model;
	}

	@PostMapping(value = "/products/")
	public ResponseEntity<Object> createProduct(@Validated @RequestBody Product product) {
		
		Product productResult = productRepository.save(product);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(productResult.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
		
		//return productResult;
	}

	@PutMapping(value = "/products/")
	public void updateProduct(@RequestBody Product product) {
		productRepository.save(product);
	}

	@DeleteMapping(value = "/products/{id}")
	@CacheEvict("product-cache")
	public void deleteProduct(@PathVariable("id") int id) {
		productRepository.deleteById(id);
	}

	@GetMapping("getName/")
	public ModelAndView sendDataToUI() {
		ModelAndView modelAndView = new ModelAndView("data");
		modelAndView.addObject("name", "Smrutiranjan Nayak !!");
		return modelAndView;
	}
	
	@GetMapping(value = "/hello-world-internationalized")
	public String Internationalized() {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}

}
