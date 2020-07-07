package com.inventory.InventoryManagementSystem.DAO;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.inventory.InventoryManagementSystem.Model.Product;
import com.inventory.InventoryManagementSystem.Model.ProductCategory;
import com.inventory.InventoryManagementSystem.Model.Report;
import com.inventory.InventoryManagementSystem.exception.BrandAlreadyExistException;
import com.inventory.InventoryManagementSystem.exception.ProductErrorCode;
import com.inventory.InventoryManagementSystem.util.Util;

@Repository("productDao")
public class ProductDAOImpl implements ProductDao {

	static ProductCategory productCategory = new ProductCategory();
	static Map<LocalDateTime, Product> addProductRequestMap = new ConcurrentHashMap<LocalDateTime, Product>();
	static Map<LocalDateTime, Product> deleteProductRequestMap = new ConcurrentHashMap<LocalDateTime, Product>();
	static {
		Map<String, Map<String, Product>> productMap = new ConcurrentHashMap<String, Map<String, Product>>();
		Product product = new Product("Pen", "Reynolds", Util.getTimeStamp(), 10, 10);
		Map<String, Product> products = new ConcurrentHashMap<String, Product>();
		products.put(product.getBrand_name(), product);
		productMap.put(product.getProduct_name(), products);
		productCategory.setProductMap(productMap);

	}

	@Override
	public Product addProduct(Product product) {
		product.setUpdate_at(Util.getTimeStamp());
		if (productCategory.getProductMap().containsKey(product.getProduct_name())) {
			if (productCategory.getProductMap().get(product.getProduct_name()).containsKey(product.getBrand_name())) {

				return null;

			} else {
				productCategory.getProductMap().get(product.getProduct_name()).put(product.getBrand_name(), product);
			}
		} else {
			Map<String, Product> products = new ConcurrentHashMap<String, Product>();
			products.put(product.getBrand_name(), product);
			productCategory.getProductMap().put(product.getProduct_name(), products);
		}

		addProductRequestMap.put(product.getUpdate_at(), product);

		return product;
	}

	@Override
	public String updateProductByPrice(String product_name, String brand_name, Integer price) {

		if (productCategory.getProductMap().containsKey(product_name)) {
			if (productCategory.getProductMap().get(product_name).containsKey(brand_name)) {
				Product currentProduct = productCategory.getProductMap().get(product_name).get(brand_name);
				currentProduct.setPrice(price);
				return "updated";
			} else
				return ProductErrorCode.BRAND_NOT_FOUND.getErrorCode();

		}
		return ProductErrorCode.PRODUCT_NOT_FOUND.getErrorCode();
	}

	@Override
	public String updateProductByQuantity(String product_name, String brand_name, Integer quantity) {

		if (productCategory.getProductMap().containsKey(product_name)) {
			if (productCategory.getProductMap().get(product_name).containsKey(brand_name)) {
				Product currentProduct = productCategory.getProductMap().get(product_name).get(brand_name);
				currentProduct.setQuantity(currentProduct.getQuantity() + quantity);
				return "updated";
			} else
				return ProductErrorCode.BRAND_NOT_FOUND.getErrorCode();

		}
		return ProductErrorCode.PRODUCT_NOT_FOUND.getErrorCode();
	}

	@Override
	public Map<String, Map<String, Product>> getProduct() {
		return productCategory.getProductMap();
	}

	@Override
	public Map<String, Product> getProductByProductName(String product_name) {

		if (productCategory.getProductMap().containsKey(product_name)) {
			return productCategory.getProductMap().get(product_name);

		}

		return null;
	}

	@Override
	public String deleteProduct(String product_name) {
		if (productCategory.getProductMap().containsKey(product_name)) {

			if (productCategory.getProductMap().get(product_name).isEmpty())
				productCategory.getProductMap().remove(product_name);
			else
				throw new BrandAlreadyExistException(ProductErrorCode.BRAND_ALREADY_EXIST,
						productCategory.getProductMap().get(product_name).toString());

			return product_name + " deleted";
		} else
			return null;
	}

	@Override
	public String deleteBrandForProduct(String product_name, String brand_name) {
		if (productCategory.getProductMap().containsKey(product_name)) {
			if (productCategory.getProductMap().get(product_name).containsKey(brand_name)) {
				Product deletedProduct = productCategory.getProductMap().get(product_name).remove(brand_name);

				deleteProductRequestMap.put(deletedProduct.getUpdate_at(), deletedProduct);
				return brand_name + " deleted";
			} else
				return ProductErrorCode.BRAND_NOT_FOUND.getErrorCode();
		}

		return ProductErrorCode.PRODUCT_NOT_FOUND.getErrorCode();
	}

	@Override
	public String purchaseProductByBrand(String product_name, String brand_name, int quantity) {
		Integer totalPrice;
		if (productCategory.getProductMap().containsKey(product_name)) {
			if (productCategory.getProductMap().get(product_name).containsKey(brand_name)) {
				Product currentProduct = productCategory.getProductMap().get(product_name).get(brand_name);
				if (currentProduct.getQuantity() >= quantity) {
					totalPrice = quantity * currentProduct.getPrice();
					if (currentProduct.getQuantity() == quantity) {
						productCategory.getProductMap().get(product_name).remove(brand_name);
						if (productCategory.getProductMap().get(product_name).size() == 0) {
							productCategory.getProductMap().remove(product_name);
						}
					} else {
						currentProduct.setQuantity(currentProduct.getQuantity() - quantity);
					}
					return totalPrice.toString();

				} else {
					return ProductErrorCode.QUANTITY_EXCEED.getErrorCode();
				}
			} else {
				return ProductErrorCode.BRAND_NOT_FOUND.getErrorCode();
			}
		}

		return ProductErrorCode.PRODUCT_NOT_FOUND.getErrorCode();
	}

	@Override
	public Report getProductsByReportingDay(Report report) {
		addProductRequestMap.entrySet();
		deleteProductRequestMap.entrySet();
		
		for(LocalDateTime dt:addProductRequestMap.keySet()) {
			if (dt.isAfter(report.getFromDateTime()) && dt.isBefore(report.getToDateTime())) {
				report.getAdded_items().add(addProductRequestMap.get(dt));
			}
		}
		
		for(LocalDateTime dt:deleteProductRequestMap.keySet()) {
			if(dt.isAfter(report.getFromDateTime()) && dt.isBefore(report.getToDateTime())) {
				report.getDeleted_items().add(deleteProductRequestMap.get(dt));
			}
		}

		return report;
	}

}
