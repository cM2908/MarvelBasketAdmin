package com.bean;

public class Book {
	
	private Integer bookId;
	private String bookName;
	private String authorName;
	private String bookType;
	private Double bookPrice;
	private Long bookStock;
	private String bookDescription;
	private String bookImages;
	private String bookStatus;
	private Seller seller;
	
	public Book() {
		// Do Nothing..
	}

	public Book(Integer bookId, String bookName, String authorName, String bookType, Double bookPrice,
			Long bookStock, String bookDescription, Seller seller , String bookImages , String bookStatus) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.authorName = authorName;
		this.bookType = bookType;
		this.bookPrice = bookPrice;
		this.bookStock = bookStock;
		this.bookDescription = bookDescription;
		this.bookImages = bookImages;
		this.bookStatus = bookStatus;
		this.seller = seller;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	public Double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(Double bookPrice) {
		this.bookPrice = bookPrice;
	}

	public Long getBookStock() {
		return bookStock;
	}

	public void setBookStock(Long bookStock) {
		this.bookStock = bookStock;
	}

	public String getBookDescription() {
		return bookDescription;
	}

	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public String getBookImages() {
		return bookImages;
	}

	public void setBookImages(String bookImages) {
		this.bookImages = bookImages;
	}

	public String getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}

	public String toString() {
		return this.getClass().getSimpleName();
	}
}
