package com.bean;

import java.util.List;

public class SellerCategory {

	private Seller seller;
	private List<Category> category;

	public SellerCategory() {
		// Do nothing
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}

	public SellerCategory(Seller seller, List<Category> category) {
		super();
		this.seller = seller;
		this.category = category;
	}

}
