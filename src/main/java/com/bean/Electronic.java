package com.bean;

public class Electronic {
	
	private Integer electronicId;
	private String electronicType;
	private String electronicName;
	private String electronicBrand;
	private String electronicDescription;
	private Double electronicPrice;
	private Long electronicStock;
	private String electronicSize;
	private String electronicColor;
	private Double electronicWeight;
	private String electronicSpecification;
	private String electronicImages;
	private String electronicStatus;
	private Seller seller;

	public Electronic() {
		// Do Nothing..
	}

	public Electronic(Integer electronicId, String electronicType, String electronicName, String electronicBrand,
			String electronicDescription, Double electronicPrice, Long electronicStock, String electronicSize, String electronicColor,
			Double electronicWeight, String electronicSpecification, Seller seller , String electronicImages , String electronicStatus) {
		super();
		this.electronicId = electronicId;
		this.electronicType = electronicType;
		this.electronicName = electronicName;
		this.electronicBrand = electronicBrand;
		this.electronicDescription = electronicDescription;
		this.electronicPrice = electronicPrice;
		this.electronicStock = electronicStock;
		this.electronicSize = electronicSize;
		this.electronicColor = electronicColor;
		this.electronicWeight = electronicWeight;
		this.electronicSpecification = electronicSpecification;
		this.electronicImages = electronicImages;
		this.electronicStatus = electronicStatus;
		this.seller = seller;
	}

	public Integer getElectronicId() {
		return electronicId;
	}

	public void setElectronicId(Integer electronicId) {
		this.electronicId = electronicId;
	}

	public String getElectronicType() {
		return electronicType;
	}

	public void setElectronicType(String electronicType) {
		this.electronicType = electronicType;
	}

	public String getElectronicName() {
		return electronicName;
	}

	public void setElectronicName(String electronicName) {
		this.electronicName = electronicName;
	}

	public String getElectronicBrand() {
		return electronicBrand;
	}

	public void setElectronicBrand(String electronicBrand) {
		this.electronicBrand = electronicBrand;
	}

	public String getElectronicDescription() {
		return electronicDescription;
	}

	public void setElectronicDescription(String electronicDescription) {
		this.electronicDescription = electronicDescription;
	}

	public Double getElectronicPrice() {
		return electronicPrice;
	}

	public void setElectronicPrice(Double electronicPrice) {
		this.electronicPrice = electronicPrice;
	}

	public Long getElectronicStock() {
		return electronicStock;
	}

	public void setElectronicStock(Long electronicStock) {
		this.electronicStock = electronicStock;
	}

	public String getElectronicSize() {
		return electronicSize;
	}

	public void setElectronicSize(String electronicSize) {
		this.electronicSize = electronicSize;
	}

	public String getElectronicColor() {
		return electronicColor;
	}

	public void setElectronicColor(String electronicColor) {
		this.electronicColor = electronicColor;
	}

	public Double getElectronicWeight() {
		return electronicWeight;
	}

	public void setElectronicWeight(Double electronicWeight) {
		this.electronicWeight = electronicWeight;
	}

	public String getElectronicSpecification() {
		return electronicSpecification;
	}

	public void setElectronicSpecification(String electronicSpecification) {
		this.electronicSpecification = electronicSpecification;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public String getElectronicImages() {
		return electronicImages;
	}

	public void setElectronicImages(String electronicImages) {
		this.electronicImages = electronicImages;
	}

	public String getElectronicStatus() {
		return electronicStatus;
	}

	public void setElectronicStatus(String electronicStatus) {
		this.electronicStatus = electronicStatus;
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}
	
}