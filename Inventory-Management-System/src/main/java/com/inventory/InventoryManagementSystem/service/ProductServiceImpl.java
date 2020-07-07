package com.inventory.InventoryManagementSystem.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.InventoryManagementSystem.DAO.ProductDao;
import com.inventory.InventoryManagementSystem.Model.Product;
import com.inventory.InventoryManagementSystem.Model.Report;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

	@Override
	public Product addProduct(Product product) {
		// TODO Auto-generated method stub

		return productDao.addProduct(product);
	}

	@Override
	public String updateProductByPrice(String product_name, String brand_name, Integer price) {

		return productDao.updateProductByPrice(product_name, brand_name, price);
	}

	@Override
	public String updateProductByQuantity(String product_name, String brand_name, Integer quantity) {

		return productDao.updateProductByQuantity(product_name, brand_name, quantity);
	}

	@Override
	public Map<String, Map<String, Product>> getProduct() {
		// TODO Auto-generated method stub
		return productDao.getProduct();
	}

	@Override
	public Map<String, Product> getProductByProductName(String product_name) {

		return productDao.getProductByProductName(product_name);
	}

	public Report getProductsByReportingDay(Report report){
		
		return productDao.getProductsByReportingDay(report);
	}
	
	@Override
	public String deleteProduct(String product_name) {
		// TODO Auto-generated method stub
		return productDao.deleteProduct(product_name);
	}

	public String deleteBrandForProduct(String product_name, String brand_name) {
		return productDao.deleteBrandForProduct(product_name, brand_name);
	}

	@Override
	public String purchaseProductByBrand(String product_name, String brand_name, int quantity) {
		// TODO Auto-generated method stub
		return productDao.purchaseProductByBrand(product_name, brand_name, quantity);
	}

}
