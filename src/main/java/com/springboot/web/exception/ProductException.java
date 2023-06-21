package com.springboot.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1860146852398140935L;

	/**
	 * @param message
	 */
	public ProductException(String message) {
		super(message);
	}

}
