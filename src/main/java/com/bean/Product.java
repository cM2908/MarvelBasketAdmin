package com.bean;

public class Product {
	
	int productId;
	String productName;
	double procuctPrice;
	int productRating;
	int sellerId;
	int catagoryId;
	
	public Product(){
		//Do nothing
	}
	public Product(int productId, String productName, double procuctPrice, int productRating, int sellerId,
			int catagoryId) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.procuctPrice = procuctPrice;
		this.productRating = productRating;
		this.sellerId = sellerId;
		this.catagoryId = catagoryId;
	}
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getProcuctPrice() {
		return procuctPrice;
	}
	public void setProcuctPrice(double procuctPrice) {
		this.procuctPrice = procuctPrice;
	}
	public int getProductRating() {
		return productRating;
	}
	public void setProductRating(int productRating) {
		this.productRating = productRating;
	}
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public int getCatagoryId() {
		return catagoryId;
	}
	public void setCatagoryId(int catagoryId) {
		this.catagoryId = catagoryId;
	}
}
