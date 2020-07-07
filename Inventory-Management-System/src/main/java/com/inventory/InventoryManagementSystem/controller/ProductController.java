package com.inventory.InventoryManagementSystem.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.inventory.InventoryManagementSystem.Model.Product;
import com.inventory.InventoryManagementSystem.Model.Purchase;
import com.inventory.InventoryManagementSystem.Model.Report;
import com.inventory.InventoryManagementSystem.exception.BrandAlreadyExistException;
import com.inventory.InventoryManagementSystem.exception.ProductErrorCode;
import com.inventory.InventoryManagementSystem.exception.ProductNotFoundException;
import com.inventory.InventoryManagementSystem.exception.QuantityExceedException;
import com.inventory.InventoryManagementSystem.service.ProductService;
import com.inventory.InventoryManagementSystem.util.Util;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Product", description = "the Product API")
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping("/product")
	public ResponseEntity<Object> addProduct(@RequestBody Product product) {

		product.setUpdate_at(Util.getTimeStamp());

		Product newProduct = productService.addProduct(product);
		if (newProduct == null)
			throw new BrandAlreadyExistException(ProductErrorCode.BRAND_ALREADY_EXIST, product.getBrand_name());
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newProduct.getProduct_name()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PatchMapping("/product/price/{product_name}/{brand_name}")
	public ResponseEntity<Object> updateProductByPrice(@PathVariable("product_name") String product_name,
			@PathVariable("brand_name") String brand_name, @QueryParam("price") Integer price) {

		String message = productService.updateProductByPrice(product_name, brand_name, price);

		if (message.equals(ProductErrorCode.PRODUCT_NOT_FOUND.getErrorCode()))
			throw new QuantityExceedException(ProductErrorCode.PRODUCT_NOT_FOUND, product_name);
		else if (message.equals(ProductErrorCode.BRAND_NOT_FOUND.getErrorCode()))
			throw new QuantityExceedException(ProductErrorCode.BRAND_NOT_FOUND, brand_name);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@PatchMapping("/product/quantity/{product_name}/{brand_name}")
	public ResponseEntity<Object> updateProductByQuantity(@PathVariable("product_name") String product_name,
			@PathVariable("brand_name") String brand_name, @QueryParam("quantity") Integer quantity) {

		String message = productService.updateProductByQuantity(product_name, brand_name, quantity);

		if (ProductErrorCode.PRODUCT_NOT_FOUND.getErrorCode().equals(message))
			throw new QuantityExceedException(ProductErrorCode.PRODUCT_NOT_FOUND, product_name);
		else if (ProductErrorCode.BRAND_NOT_FOUND.getErrorCode().equals(message))
			throw new QuantityExceedException(ProductErrorCode.BRAND_NOT_FOUND, brand_name);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@GetMapping("/product")
	public Map<String, Map<String, Product>> getAllProducts() {

		return productService.getProduct();
	}

	@GetMapping("/product/{product_name}")
	public Map<String, Product> getProductByProductName(@PathVariable("product_name") String product_name) {

		Map<String, Product> existingProduct = productService.getProductByProductName(product_name);
		if (existingProduct == null)
			throw new ProductNotFoundException(ProductErrorCode.PRODUCT_NOT_FOUND, product_name);
		return existingProduct;
	}

	@PostMapping("/report")
	public Report getProductsByReportingDay(@QueryParam("fromDateTime") String fromDateTime,
			@QueryParam("toDateTime") String toDateTime) {
		Report report = new Report(fromDateTime, toDateTime);
		return productService.getProductsByReportingDay(report);
	}

	@DeleteMapping("/product/{product_name}")
	public ResponseEntity<Object> deleteProduct(@PathVariable("product_name") String product_name) {

		String message = productService.deleteProduct(product_name);
		if (message == null)
			throw new ProductNotFoundException(ProductErrorCode.PRODUCT_NOT_FOUND, product_name);
		return new ResponseEntity<>(message, HttpStatus.OK);

	}

	@DeleteMapping("/product/{product_name}/brand/{brand_name}")
	public ResponseEntity<Object> deleteBrandForProduct(@PathVariable("product_name") String product_name,
			@PathVariable("brand_name") String brand_name) {

		String message = productService.deleteBrandForProduct(product_name, brand_name);
		if (message.equals(ProductErrorCode.PRODUCT_NOT_FOUND.getErrorCode()))
			throw new QuantityExceedException(ProductErrorCode.PRODUCT_NOT_FOUND, product_name);
		else if (message.equals(ProductErrorCode.BRAND_NOT_FOUND.getErrorCode()))
			throw new QuantityExceedException(ProductErrorCode.BRAND_NOT_FOUND, brand_name);
		return new ResponseEntity<>(message, HttpStatus.OK);

	}

	@PostMapping("/purchase")

	public ResponseEntity<Object> multiplePurchase(@RequestBody List<Purchase> purchaseList) {

		Integer cost = 0;
		for (Purchase purchase : purchaseList) {

			String message = productService.purchaseProductByBrand(purchase.getProduct_name(), purchase.getBrand_name(),
					purchase.getQuantity());
			if (ProductErrorCode.QUANTITY_EXCEED.getErrorCode().equals(message))
				throw new QuantityExceedException(ProductErrorCode.QUANTITY_EXCEED, purchase.getBrand_name());
			else if (ProductErrorCode.PRODUCT_NOT_FOUND.getErrorCode().equals(message))
				throw new QuantityExceedException(ProductErrorCode.PRODUCT_NOT_FOUND, purchase.getProduct_name());
			else if (ProductErrorCode.BRAND_NOT_FOUND.getErrorCode().equals(message))
				throw new QuantityExceedException(ProductErrorCode.BRAND_NOT_FOUND, purchase.getBrand_name());
			cost += Integer.parseInt(message);
		}

		return new ResponseEntity<>("Total cost " + cost, HttpStatus.OK);
	}

}
