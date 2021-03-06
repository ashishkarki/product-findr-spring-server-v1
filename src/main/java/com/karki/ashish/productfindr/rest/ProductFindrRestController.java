package com.karki.ashish.productfindr.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karki.ashish.productfindr.entity.Product;
import com.karki.ashish.productfindr.error.ProductNotFoundException;

// @CrossOrigin(methods= {})
@RestController
@RequestMapping("/api")
public class ProductFindrRestController {
	private List<Product> products = new ArrayList<Product>();

	@PostConstruct
	public void loadHardCodedData() {
		// postcontruct will cause this method to only execute once when the bean is
		// being created
		products.add(new Product(1, "banana", "2/28/2019", "4d", "Produce", "$2.99", "lb", 1, "$0.44"));
		products.add(new Product(2, "headache-gone", "11/11/2018", "365d", "Pharmacy", "$5.99", "Each", 1, "$0.90"));
		products.add(new Product(3, "sleep-well", "3/9/2019", "365d", "Furnishing", "$1000", "Each", 1, "$1000"));
	}

	// default end point
	@GetMapping("")
	public String defaultEndPoint() {
		return "Hello! you have reached the spring mvc based server.";
	}

	@GetMapping("/products")
	public List<Product> getAllProducts() {

		return this.products;
	}

	// endpoint to retrieve particular product
	@GetMapping("/products/{productId}")
	public Product getProduct(@PathVariable int productId) {
		// quick error check
		if (productId >= products.size() || productId < 0) {
			throw new ProductNotFoundException("Product ID not found - " + productId);
		}

		return products.get(productId); // keep it simple for now
	}
}
