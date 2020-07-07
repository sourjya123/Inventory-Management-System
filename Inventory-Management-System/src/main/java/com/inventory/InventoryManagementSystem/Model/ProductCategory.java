package com.inventory.InventoryManagementSystem.Model;

import java.util.Map;

public class ProductCategory {

	private Map<String, Map<String, Product>> productMap;

	public ProductCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductCategory(Map<String, Map<String, Product>> productMap) {
		super();
		this.productMap = productMap;
	}

	public Map<String, Map<String, Product>> getProductMap() {
		return productMap;
	}

	public void setProductMap(Map<String, Map<String, Product>> productMap) {
		this.productMap = productMap;
	}

	@Override
	public String toString() {
		return "ProductCategory [ productMap=" + productMap + "]";
	}

}
