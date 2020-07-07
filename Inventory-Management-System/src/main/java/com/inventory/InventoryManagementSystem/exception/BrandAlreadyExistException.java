package com.inventory.InventoryManagementSystem.exception;

public class BrandAlreadyExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String errorCode;
	String errorMessage;

	public BrandAlreadyExistException(String message, String code) {
		super(message);
		errorMessage = message;
		errorCode = code;
	}

	public BrandAlreadyExistException(ProductErrorCode errorCode, String... param) {
		this(String.format(errorCode.getErrorDesc(), param), errorCode.getErrorCode());
	}

}
