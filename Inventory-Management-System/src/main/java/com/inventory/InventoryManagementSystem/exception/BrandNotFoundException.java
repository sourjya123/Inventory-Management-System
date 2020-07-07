package com.inventory.InventoryManagementSystem.exception;

public class BrandNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String errorCode;
	String errorMessage;

	public BrandNotFoundException(String message, String code) {
		super(message);
		errorMessage = message;
		errorCode = code;
	}

	public BrandNotFoundException(ProductErrorCode errorCode, String... param) {
		this(String.format(errorCode.getErrorDesc(), param), errorCode.getErrorCode());
	}

}
