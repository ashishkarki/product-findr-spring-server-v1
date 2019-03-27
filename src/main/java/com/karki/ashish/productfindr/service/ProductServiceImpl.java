package com.karki.ashish.productfindr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.karki.ashish.productfindr.dao.ProductDAO;
import com.karki.ashish.productfindr.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;

	@Override
	@Transactional
	public List<Product> getAllProducts() {
		return productDAO.getAllProducts();
	}

	@Override
	@Transactional
	public List<Product> getSearchedProducts(String searchString) {
		return productDAO.getSearchedProducts(searchString);
	}
}
