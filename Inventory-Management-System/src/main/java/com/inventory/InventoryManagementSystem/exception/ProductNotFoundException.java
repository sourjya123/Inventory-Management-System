package com.inventory.InventoryManagementSystem.exception;

public class ProductNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String errorCode;
	String errorMessage;

	public ProductNotFoundException(String message, String code) {
		super(message);
		errorMessage = message;
		errorCode = code;
	}

	public ProductNotFoundException(ProductErrorCode errorCode, String... param) {
		this(String.format(errorCode.getErrorDesc(), param), errorCode.getErrorCode());
	}

}
