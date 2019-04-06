package com.karki.ashish.productfindr.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

		CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
		CriteriaQuery<Product> criteria = criteriaBuilder.createQuery(Product.class);
		Root<Product> root = criteria.from(Product.class);
		criteria.select(root);

		Predicate restrictions;
		if (searchString.matches("\\d+")) {
			restrictions = criteriaBuilder.or(criteriaBuilder.equal(root.get("id"), Integer.parseInt(searchString)),
					criteriaBuilder.equal(root.get("xFor"), Integer.parseInt(searchString)));
		} else {
			restrictions = criteriaBuilder.or(criteriaBuilder.like(root.get("description"), "%" + searchString + "%"),
					criteriaBuilder.like(root.get("lastSold"), "%" + searchString + "%"),
					criteriaBuilder.like(root.get("shelfLife"), "%" + searchString + "%"),
					criteriaBuilder.like(root.get("department"), "%" + searchString + "%"),
					criteriaBuilder.like(root.get("price"), "%" + searchString + "%"),
					criteriaBuilder.like(root.get("unit"), "%" + searchString + "%"),
					criteriaBuilder.like(root.get("cost"), "%" + searchString + "%"));
		}

		criteria.where(restrictions);

		// JUST A DEMO, WE CAN ALSO USE NATIVE QUERY LIKE THIS
//		final String nativeSqlString = "select * from products product0_ where product0_.price like '%"+ searchString + "%' or product0_.cost like '%\"+ searchString + \"%'";
//		Query<Product> query = currentSession.createNativeQuery(nativeSqlString, Product.class);

		Query<Product> query = currentSession.createQuery(criteria);
		List<Product> results = query.getResultList();

		return results;
	}

	@Override
	public void saveProduct(Product savedProduct) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.saveOrUpdate(savedProduct);
	}

	@Override
	public void deleteProduct(int deletedProductId) {
		Session currentSession = sessionFactory.getCurrentSession();

		@SuppressWarnings("rawtypes")
		Query deletionQuery = currentSession.createQuery("delete from Product where id=:Id");
		deletionQuery.setParameter("Id", deletedProductId);

		deletionQuery.executeUpdate();
	}

}
