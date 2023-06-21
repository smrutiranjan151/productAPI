/**
 * 
 */
package com.springboot.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.web.entities.Product;

/**
 * @author smrut
 *
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
