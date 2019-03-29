package com.karki.ashish.productfindr.dao;

import java.util.List;

import com.karki.ashish.productfindr.entity.Product;

public interface ProductDAO {
	public List<Product> getAllProducts();

	public List<Product> getSearchedProducts(String searchString);

	public void saveProduct(Product savedProduct);

	public void deleteProduct(int deletedProductId);
}
