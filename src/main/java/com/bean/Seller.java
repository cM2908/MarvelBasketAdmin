package com.bean;

public class Seller {
	
	private Integer sellerId;
	private String sellerName;
	private String sellerEmail;
	private String sellerContact;
	private String sellerPassword;
	private String shopName;
	private String shopAddress;
	private String sellerStatus;
	
	public Seller(){
		//Do nothing	
	}
	
	public Seller(Integer sellerId, String sellerName, String sellerEmail, String sellerContact, String sellerPassword,
			String shopName, String shopAddress) {
		super();
		this.sellerId = sellerId;
		this.sellerName = sellerName;
		this.sellerEmail = sellerEmail;
		this.sellerContact = sellerContact;
		this.sellerPassword = sellerPassword;
		this.shopName = shopName;
		this.shopAddress = shopAddress;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getSellerEmail() {
		return sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	public String getSellerContact() {
		return sellerContact;
	}

	public void setSellerContact(String sellerContact) {
		this.sellerContact = sellerContact;
	}

	public String getSellerPassword() {
		return sellerPassword;
	}

	public void setSellerPassword(String sellerPassword) {
		this.sellerPassword = sellerPassword;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}

	public String getSellerStatus() {
		return sellerStatus;
	}

	public void setSellerStatus(String sellerStatus) {
		this.sellerStatus = sellerStatus;
	}
}
