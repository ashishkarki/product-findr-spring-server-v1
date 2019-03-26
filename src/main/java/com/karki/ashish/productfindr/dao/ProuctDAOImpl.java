package com.karki.ashish.productfindr.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.karki.ashish.productfindr.entity.Product;

@Repository
public class ProuctDAOImpl implements ProductDAO {

	// inject hibernate session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Product> getAllProducts() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Product> productsQuery = currentSession.createQuery("from Product", Product.class);

		List<Product> products = productsQuery.getResultList();

		return products;
	}

}
