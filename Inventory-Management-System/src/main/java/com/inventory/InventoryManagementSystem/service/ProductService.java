package com.inventory.InventoryManagementSystem.service;

import java.util.Map;

import com.inventory.InventoryManagementSystem.Model.Product;
import com.inventory.InventoryManagementSystem.Model.Report;

public interface ProductService {

	public Product addProduct(Product product);

	public String updateProductByPrice(String product_name, String brand_name, Integer price);
	
	public String updateProductByQuantity(String product_name, String brand_name, Integer quantity);

	public Map<String, Map<String, Product>> getProduct();
	
	public Map<String, Product> getProductByProductName(String product_name);
	
	public Report getProductsByReportingDay(Report report);

	public String deleteProduct(String product_name);
	
	public String deleteBrandForProduct(String product_name, String brand_name);

	public String purchaseProductByBrand(String product_name, String brand_name, int quantity);
}
