package com.inventory.InventoryManagementSystem.exception;

public class QuantityExceedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String errorCode;
	String errorMessage;

	public QuantityExceedException(String message, String code) {
		super(message);
		errorMessage = message;
		errorCode = code;
	}

	public QuantityExceedException(ProductErrorCode errorCode, String... param) {
		this(String.format(errorCode.getErrorDesc(), param), errorCode.getErrorCode());
	}

}
