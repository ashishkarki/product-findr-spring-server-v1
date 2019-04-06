package com.karki.ashish.productfindr.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ProductErrorResponse> handleException(ProductNotFoundException pnfe) {
		ProductErrorResponse errorResponse = new ProductErrorResponse();
		errorResponse.setMessage(pnfe.getMessage());
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponse.setTimestamp(System.currentTimeMillis());

		return new ResponseEntity<ProductErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

	// catch all exception handling
	@ExceptionHandler
	public ResponseEntity<ProductErrorResponse> handleException(Exception e) {
		ProductErrorResponse errorResponse = new ProductErrorResponse();
		errorResponse.setMessage(e.getMessage());
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setTimestamp(System.currentTimeMillis());

		return new ResponseEntity<ProductErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}
