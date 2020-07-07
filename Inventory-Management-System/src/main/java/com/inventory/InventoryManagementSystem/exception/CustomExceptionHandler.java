package com.inventory.InventoryManagementSystem.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.inventory.InventoryManagementSystem.Model.CustomException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	protected ResponseEntity<Object> productNotFoundException(ProductNotFoundException ex, WebRequest request) {

		CustomException customException = new CustomException(ex.getMessage(), request.getDescription(false),
				new Date(), ex.errorCode);

		return new ResponseEntity<>(customException, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(QuantityExceedException.class)
	protected ResponseEntity<Object> quantityExceedException(QuantityExceedException ex, WebRequest request) {

		CustomException customException = new CustomException(ex.getMessage(), request.getDescription(false),
				new Date(), ex.errorCode);

		return new ResponseEntity<>(customException, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BrandNotFoundException.class)
	protected ResponseEntity<Object> brandNotFoundException(BrandNotFoundException ex, WebRequest request) {

		CustomException customException = new CustomException(ex.getMessage(), request.getDescription(false),
				new Date(), ex.errorCode);

		return new ResponseEntity<>(customException, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BrandAlreadyExistException.class)
	protected ResponseEntity<Object> brandAlreadyExistException(BrandAlreadyExistException ex, WebRequest request) {

		CustomException customException = new CustomException(ex.getMessage(), request.getDescription(false),
				new Date(), ex.errorCode);

		return new ResponseEntity<>(customException, HttpStatus.CONFLICT);
	}
}
