package com.karki.ashish.productfindr.entity;

public class Product {
	private int id;
	private String description;
	private String lastSold;
	private String shelfLife;
	private String department;
	private String price;
	private String unit;
	private int xFor;
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
