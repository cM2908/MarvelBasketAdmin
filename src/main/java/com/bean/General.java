package com.bean;

public class General {
	
	private Integer productId;
	private String productType;
	private String productName;
	private String productBrand;
	private String productDescription;
	private Double productPrice;
	private Long productStock;
	private String productImages;
	private String productStatus;
	private Seller seller;
	
	public General() {
		super();
		// Do Nothing..
	}

	public General(Integer productId, String productType, String productName, String productBrand,
			String productDescription, Double productPrice, Long productStock, Seller seller , String productImages , String productStatus) {
		super();
		this.productId = productId;
		this.productType = productType;
		this.productName = productName;
		this.productBrand = productBrand;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.productStock = productStock;
		this.productImages = productImages;
		this.productStatus = productStatus;
		this.seller = seller;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public Long getProductStock() {
		return productStock;
	}

	public void setProductStock(Long productStock) {
		this.productStock = productStock;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public String getProductImages() {
		return productImages;
	}

	public void setProductImages(String productImages) {
		this.productImages = productImages;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
