package com.bean;

public class Fashion {
	
	private Integer fashionId;
	private String fashionType;
	private String fashionBuyerType;
	private String fashionName;
	private String fashionBrand;
	private String fashionColor;
	private String fashionDescription;
	private String fashionImages;
	private String fashionStatus;
	private Seller seller;

	public Fashion() {
		// Do Nothing..
	}

	public Fashion(Integer fashionId, String fashionType, String fashionBuyerType, String fashionName,
			String fashionBrand, String fashionColor, String fashionDescription, Seller seller , String fashionImages , String fashionStatus) {
		super();
		this.fashionId = fashionId;
		this.fashionType = fashionType;
		this.fashionBuyerType = fashionBuyerType;
		this.fashionName = fashionName;
		this.fashionBrand = fashionBrand;
		this.fashionColor = fashionColor;
		this.fashionDescription = fashionDescription;
		this.fashionImages = fashionImages;
		this.fashionStatus = fashionStatus;
		this.seller = seller;
	}

	public Integer getFashionId() {
		return fashionId;
	}

	public void setFashionId(Integer fashionId) {
		this.fashionId = fashionId;
	}

	public String getFashionType() {
		return fashionType;
	}

	public void setFashionType(String fashionType) {
		this.fashionType = fashionType;
	}

	public String getFashionBuyerType() {
		return fashionBuyerType;
	}

	public void setFashionBuyerType(String fashionBuyerType) {
		this.fashionBuyerType = fashionBuyerType;
	}

	public String getFashionName() {
		return fashionName;
	}

	public void setFashionName(String fashionName) {
		this.fashionName = fashionName;
	}

	public String getFashionBrand() {
		return fashionBrand;
	}

	public void setFashionBrand(String fashionBrand) {
		this.fashionBrand = fashionBrand;
	}

	public String getFashionDescription() {
		return fashionDescription;
	}

	public void setFashionDescription(String fashionDescription) {
		this.fashionDescription = fashionDescription;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public String getFashionImages() {
		return fashionImages;
	}

	public void setFashionImages(String fashionImages) {
		this.fashionImages = fashionImages;
	}

	public String getFashionStatus() {
		return fashionStatus;
	}

	public void setFashionStatus(String fashionStatus) {
		this.fashionStatus = fashionStatus;
	}

	public String toString() {
		return this.getClass().getSimpleName();
	}

	public String getFashionColor() {
		return fashionColor;
	}

	public void setFashionColor(String fashionColor) {
		this.fashionColor = fashionColor;
	}
}