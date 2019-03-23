package com.karki.ashish.productfindr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="description")
	private String description;
	
	@Column(name="lastSold")
	private String lastSold;
	
	@Column(name="shelfLife")
	private String shelfLife;
	
	@Column(name="department")
	private String department;
	
	@Column(name="price")
	private String price;
	
	@Column(name="unit")
	private String unit;
	
	@Column(name="xFor")
	private int xFor;
	
	@Column(name="cost")
	private String cost;

	public Product() {
	}

	public Product(int id, String description, String lastSold, String shelfLife, String department, String price,
			String unit, int xFor, String cost) {
		this.id = id;
		this.description = description;
		this.lastSold = lastSold;
		this.shelfLife = shelfLife;
		this.department = department;
		this.price = price;
		this.unit = unit;
		this.xFor = xFor;
		this.cost = cost;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLastSold() {
		return lastSold;
	}

	public void setLastSold(String lastSold) {
		this.lastSold = lastSold;
	}

	public String getShelfLife() {
		return shelfLife;
	}

	public void setShelfLife(String shelfLife) {
		this.shelfLife = shelfLife;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getxFor() {
		return xFor;
	}

	public void setxFor(int xFor) {
		this.xFor = xFor;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

}
