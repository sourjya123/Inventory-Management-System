package com.inventory.InventoryManagementSystem.exception;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ProductErrorCode {

	PRODUCT_NOT_FOUND("5000", "Product Not Found", "[%s] not found"),
	BRAND_NOT_FOUND("5001", "Brand Not Found", "[%s] Brand not found"),
	QUANTITY_EXCEED("5002", "Quantity Exceed", "[%s] Quantity Exceed"),
	BRAND_ALREADY_EXIST("5003", "Brand Already Exist", "[%s] Brand Already Exist");

	private static final Map<String, ProductErrorCode> resourceErrorCodeMap = new HashMap<String, ProductErrorCode>();

	static {
		for (ProductErrorCode resourceErrorCode : EnumSet.allOf(ProductErrorCode.class))
			resourceErrorCodeMap.put(resourceErrorCode.getErrorCode(), resourceErrorCode);
	}

	private String errorCode;
	private String errorName;
	private String errorDesc;

	private ProductErrorCode(String errorCode, String errorName, String errorDesc) {
		this.errorCode = errorCode;
		this.errorName = errorName;
		this.errorDesc = errorDesc;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorName() {
		return errorName;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public static ProductErrorCode get(String errorCode) {
		return resourceErrorCodeMap.get(errorCode);
	}

}
