package com.karki.ashish.productfindr.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karki.ashish.productfindr.entity.Product;
import com.karki.ashish.productfindr.error.ProductNotFoundException;
import com.karki.ashish.productfindr.service.ProductService;

// @CrossOrigin(methods= {})
@RestController
@RequestMapping("/api")
public class ProductFindrRestController {
	@Autowired
	ProductService productService;

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
		return productService.getAllProducts();
	}

	// endpoint to retrieve particular product
	@GetMapping("/products/{searchString:.+}")
	public List<Product> getProduct(@PathVariable String searchString) {
		// quick error check
		if (searchString.length() == 0) {
			throw new ProductNotFoundException("Product ID not found - " + searchString);
		}

		final List<Product> resultList = productService.getSearchedProducts(searchString);

		if (resultList == null || resultList.size() == 0) {
			throw new ProductNotFoundException("No Products meet search criteria - " + searchString);
		}

		return resultList;
	}

	@PostMapping("/products")
	public Product saveProduct(@RequestBody Product savedProduct) {
		/*
		 * Also set the ID field to zero so that (if it is set in the body) we force
		 * hibernate to execute an insert instead of an update
		 */
		savedProduct.setId(0);

		productService.saveProduct(savedProduct);

		return savedProduct;
	}

	@PutMapping("/products")
	public Product updateProduct(@RequestBody Product updatedProduct) {
		/*
		 * we don't set the Id to 0 here and let whatever id from body to pass, so
		 * hibernate performs an update
		 */
		productService.saveProduct(updatedProduct);

		return updatedProduct;
	}

	@DeleteMapping("/products/{deletedProductId}")
	public String deleteProduct(@PathVariable int deletedProductId) {
		// some error checking before deleting
		List<Product> tempProduct = productService.getSearchedProducts(deletedProductId + ""); // we get only one thing based on unique ID
		if(null == tempProduct || tempProduct.size() == 0) {
			throw new ProductNotFoundException("Product with this ID not found - " + deletedProductId);
		}
		
		productService.deleteProduct(deletedProductId);
		
		return "Deleted Product Id - " + deletedProductId;
	}
}
