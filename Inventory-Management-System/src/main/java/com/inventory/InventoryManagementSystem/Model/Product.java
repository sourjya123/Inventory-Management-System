package com.inventory.InventoryManagementSystem.Model;

import java.time.LocalDateTime;

public class Product {

	private String product_name;
	private String brand_name;
	private LocalDateTime update_at;
	private Integer price;
	private Integer quantity;

	public Product(String product_name, String brand_name, LocalDateTime update_at, Integer price, Integer quantity) {

		this.product_name = product_name;
		this.brand_name = brand_name;
		this.update_at = update_at;
		this.price = price;
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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(LocalDateTime update_at) {
		this.update_at = update_at;
	}

	@Override
	public String toString() {
		return "Product [product_name=" + product_name + ", brand_name=" + brand_name + ", update_at=" + update_at
				+ ", price=" + price + ", quantity=" + quantity + "]";
	}

}
