package com.karki.ashish.productfindr.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.karki.ashish.productfindr.entity.Product;

@Repository
public class ProuctDAOImpl implements ProductDAO {

	// inject hibernate session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Product> getAllProducts() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Product> productsQuery = currentSession.createQuery("from Product", Product.class);
		List<Product> products = productsQuery.getResultList();

		return products;
	}

	@Override
	public List<Product> getSearchedProducts(String searchString) {
		Session currentSession = sessionFactory.getCurrentSession();

		String hqlString = "FROM Product p WHERE p.id=:productId";
		Query<Product> searchQuery = currentSession.createQuery("FROM Product p WHERE p.id=:productId", Product.class);
		searchQuery.setParameter("productId", searchString);

		return searchQuery.getResultList();
	}

}
