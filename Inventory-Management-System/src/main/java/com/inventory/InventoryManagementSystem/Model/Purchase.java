package com.inventory.InventoryManagementSystem.Model;

public class Purchase {

	private String product_name;
	private String brand_name;
	private Integer quantity;

	public Purchase(String product_name, String brand_name, Integer quantity) {

		this.product_name = product_name;
		this.brand_name = brand_name;
		this.quantity = quantity;

	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Purchase [product_name=" + product_name + ", brand_name=" + brand_name + ", quantity=" + quantity + "]";
	}

	
}
