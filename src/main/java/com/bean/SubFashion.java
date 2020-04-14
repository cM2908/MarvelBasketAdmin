package com.bean;

public class SubFashion {

	private Integer subfashionId;
	private Double fashionPrice;
	private Long fashionStock;
	private String fashionSize;

	public SubFashion() {
		// Do Nothing..
	}

	public SubFashion(Integer subfashionId, Double fashionPrice, Long fashionStock, String fashionSize) {
		super();
		this.subfashionId = subfashionId;
		this.fashionPrice = fashionPrice;
		this.fashionStock = fashionStock;
		this.fashionSize = fashionSize;
	}

	public Integer getSubfashionId() {
		return subfashionId;
	}

	public void setSubfashionId(Integer subfashionId) {
		this.subfashionId = subfashionId;
	}

	public Double getFashionPrice() {
		return fashionPrice;
	}

	public void setFashionPrice(Double fashionPrice) {
		this.fashionPrice = fashionPrice;
	}

	public Long getFashionStock() {
		return fashionStock;
	}

	public void setFashionStock(Long fashionStock) {
		this.fashionStock = fashionStock;
	}

	public String getFashionSize() {
		return fashionSize;
	}

	public void setFashionSize(String fashionSize) {
		this.fashionSize = fashionSize;
	}

}
